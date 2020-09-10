package com.zzz.videoserver;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.trtc.v20190722.models.EncodeParams;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.impl.TransCodeParam;
import com.zzz.videoserver.utils.TencentRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TencentRequestUtilTest {
    @Autowired
    private TrtcClient client;

    @Value("${tencent.app.sdkAppId}")
    private String id;


    @Test
    public void testRequest() {
        Map<String, Object> param = new HashMap<>();
        param.put("SdkAppId", id);
        param.put("RoomId", 123);
        AbstractModel resp =
                TencentRequestUtil.request(TencentRequestType.DISMISS_ROOM, JSON.toJSONString(param), client);
        System.out.println(JSON.toJSON(resp));
    }

    @Test
    public void testRandom() {
        System.out.println("start producing");
        Random random = new Random();

        System.out.println(random.nextInt(2));
    }
}
