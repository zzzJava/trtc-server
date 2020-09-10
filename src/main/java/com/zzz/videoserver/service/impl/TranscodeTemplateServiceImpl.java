package com.zzz.videoserver.service.impl;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.TrtcParams;
import com.zzz.videoserver.service.TemplateService;
import com.zzz.videoserver.utils.TencentRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranscodeTemplateServiceImpl implements TemplateService {
    @Autowired
    private VodClient client;

    @Override
    public AbstractModel getAllTemplates(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.DESCRIBE_TRANSCODE_TEMP, params.toJSONString(), client);
    }

    @Override
    public AbstractModel createTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.CREATE_TRANSCODE_TEMP, params.toJSONString(), client);
    }

    @Override
    public AbstractModel deleteTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.DELETE_TRANSCODE_TEMP, params.toJSONString(), client);
    }

    @Override
    public AbstractModel modifyTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.MODIFY_TRANSCODE_TEMP, params.toJSONString(), client);
    }
}
