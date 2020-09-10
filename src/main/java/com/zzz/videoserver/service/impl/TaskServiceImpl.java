package com.zzz.videoserver.service.impl;

import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.zzz.videoserver.enums.TencentRequestType;
import com.zzz.videoserver.model.params.TrtcParams;
import com.zzz.videoserver.service.TaskTemplateService;
import com.zzz.videoserver.utils.TencentRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskTemplateService {
    @Autowired
    private VodClient client;

    @Override
    public AbstractModel getTasksTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.DESCRIBE_TASK_TEMP, params.toJSONString(), client);
    }

    @Override
    public AbstractModel createTaskTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.CREATE_TASK_TEMP, params.toJSONString(), client);
    }

    @Override
    public AbstractModel deleteTaskTemplate(TrtcParams params) {
        return TencentRequestUtil.request(TencentRequestType.DELETE_TASK_TEMP, params.toJSONString(), client);
    }

}
