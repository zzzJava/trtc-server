package com.zzz.videoserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.TrtcParams;
import com.zzz.videoserver.service.MeetingService;
import com.zzz.videoserver.utils.TencentRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private TrtcClient client;

    @Override
    public AbstractModel startMix(TrtcParams trtcParams) {
        return this.invoke(TencentRequestType.STRAT_MCU_MIX, trtcParams);
    }

    @Override
    public AbstractModel stopMix(TrtcParams trtcParams) {
        return this.invoke(TencentRequestType.STOP_MCU_MIX, trtcParams);
    }

    @Override
    public AbstractModel removeUsers(TrtcParams trtcParams) {
        return this.invoke(TencentRequestType.REMOVE_USER, trtcParams);
    }

    @Override
    public AbstractModel dismissRoom(TrtcParams trtcParams) {
        return this.invoke(TencentRequestType.DISMISS_ROOM, trtcParams);
    }

    private AbstractModel invoke(TencentRequestType type, TrtcParams trtcParams) {
        return TencentRequestUtil.request(type, trtcParams.toJSONString(), client);
    }
}
