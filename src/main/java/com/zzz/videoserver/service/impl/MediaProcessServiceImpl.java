package com.zzz.videoserver.service.impl;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.TrtcParams;
import com.zzz.videoserver.service.MediaProcessService;
import com.zzz.videoserver.utils.TencentRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaProcessServiceImpl implements MediaProcessService {
    @Autowired
    private VodClient client;

    @Override
    public AbstractModel processByProcedure(TrtcParams param) {
        return TencentRequestUtil.request(TencentRequestType.MEDIA_PROCESS_BY_PROCEDURE, param.toJSONString(), client);
    }
}
