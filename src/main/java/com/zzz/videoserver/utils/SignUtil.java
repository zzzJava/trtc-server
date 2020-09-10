package com.zzz.videoserver.utils;

// 使用旧版本 base64 编解码实现增强兼容性
import org.json.JSONException;
import org.springframework.cache.annotation.Cacheable;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.nio.charset.Charset;

import java.util.Arrays;
import java.util.zip.Deflater;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/**
 * 腾讯云提供，提供两种签名：
 * 1）客户端访问腾讯云api签名
 * 2）客户端上传文件签名
 */
public class SignUtil {
    private long sdkappid;
    private String key;

    public SignUtil(long sdkappid, String key, String secretId, String secretKey) {
        this.sdkappid = sdkappid;
        this.key = key;
        this.secretId = secretId;
        this.secretKey = secretKey;
    }

    /**
     * 下面四个属性用于上传文件签名
     */
    private String secretId;
    private String secretKey;
    private static final String HMAC_ALGORITHM = "HmacSHA1";
    private static final String CONTENT_CHARSET = "UTF-8";

    /**
     * 上传签名
     * @param byte1
     * @param byte2
     * @return
     */
    public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        return byte3;
    }

    private String base64Encode(byte[] buffer) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(buffer);
    }

    @Cacheable(value = {"uploadSign"}, key = "#userId")
    public String getUploadSignature(String userId) throws Exception {
        String strSign = "";
        String contextStr = "";
        long startTime = System.currentTimeMillis();
        long endTime = (startTime + 24*3600*1000);
        System.out.println(secretId);
        contextStr += "secretId=" + java.net.URLEncoder.encode(secretId, "utf8");
        contextStr += "&currentTimeStamp=" + startTime;
        contextStr += "&expireTime=" + endTime;
        contextStr += "&random=" + startTime;

        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(this.secretKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
            mac.init(secretKey);

            byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
            byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
            strSign = base64Encode(sigBuf);
            strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            throw e;
        }
        return strSign;
    }


    private String hmacsha256(String identifier, long currTime, long expire, String base64Userbuf) {
        String contentToBeSigned = "TLS.identifier:" + identifier + "\n"
                + "TLS.sdkappid:" + sdkappid + "\n"
                + "TLS.time:" + currTime + "\n"
                + "TLS.expire:" + expire + "\n";
        if (null != base64Userbuf) {
            contentToBeSigned += "TLS.userbuf:" + base64Userbuf + "\n";
        }
        try {
            byte[] byteKey = key.getBytes("UTF-8");
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA256");
            hmac.init(keySpec);
            byte[] byteSig = hmac.doFinal(contentToBeSigned.getBytes("UTF-8"));
            return (new BASE64Encoder().encode(byteSig)).replaceAll("\\s*", "");
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (InvalidKeyException  e) {
            return "";
        }
    }

    private String genSig(String identifier, long expire, byte[] userbuf) throws JSONException {

        long currTime = System.currentTimeMillis()/1000;

        JSONObject sigDoc = new JSONObject();
        sigDoc.put("TLS.ver", "2.0");
        sigDoc.put("TLS.identifier", identifier);
        sigDoc.put("TLS.sdkappid", sdkappid);
        sigDoc.put("TLS.expire", expire);
        sigDoc.put("TLS.time", currTime);

        String base64UserBuf = null;
        if (null != userbuf) {
            base64UserBuf = new BASE64Encoder().encode(userbuf);
            sigDoc.put("TLS.userbuf", base64UserBuf);
        }
        String sig = hmacsha256(identifier, currTime, expire, base64UserBuf);
        if (sig.length() == 0) {
            return "";
        }
        sigDoc.put("TLS.sig", sig);
        Deflater compressor = new Deflater();
        compressor.setInput(sigDoc.toString().getBytes(Charset.forName("UTF-8")));
        compressor.finish();
        byte [] compressedBytes = new byte[2048];
        int compressedBytesLength = compressor.deflate(compressedBytes);
        compressor.end();
        return (new String(Base64URL.base64EncodeUrl(Arrays.copyOfRange(compressedBytes,
                0, compressedBytesLength)))).replaceAll("\\s*", "");
    }

    @Cacheable(value = {"apiSign"}, key = "#identifier")
    public String genSig(String identifier, long expire) throws JSONException {
        return genSig(identifier, expire, null);
    }

    public String genSigWithUserBuf(String identifier, long expire, byte[] userbuf) throws JSONException {
        return genSig(identifier, expire, userbuf);
    }

    public void setSdkappid(long sdkappid) {
        this.sdkappid = sdkappid;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getSdkappid() {
        return sdkappid;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
