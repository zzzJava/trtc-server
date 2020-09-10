package com.zzz.videoserver.utils;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.TrtcParams;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TencentRequestUtil {
    /**
     *
     * @param requestType: 指定请求枚举类型，其中封装了请求类型、响应类型、rest方法接口
     * @param params: 请求参数，参数类型在调用之前需要检查
     * @return: request中封装的响应类型，失败时返回空
     */
    public static AbstractModel request(TencentRequestType requestType, String params, AbstractClient client) {
        Class requestClass = requestType.getRequest();
        Class responseClass = requestType.getResponse();
        String methodName = requestType.getMethod();
        Object resp = null;
        try {
            Method reqMethod = requestClass.getMethod("fromJsonString", String.class, Class.class);
            Object realRequest = reqMethod.invoke(null, params, requestClass);
            Method clientMethod = client.getClass().getDeclaredMethod(methodName, requestClass);
            resp = clientMethod.invoke(client, realRequest);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (resp != null) {
            return (AbstractModel)responseClass.cast(resp);
        }
        return null;
    }
}
