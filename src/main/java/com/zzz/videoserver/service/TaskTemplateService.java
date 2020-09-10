package com.zzz.videoserver.service;

import com.tencentcloudapi.common.AbstractModel;
import com.zzz.videoserver.model.params.TrtcParams;

public interface TaskTemplateService {
    public AbstractModel getTasksTemplate(TrtcParams params);


    /**
     * 创建任务流模板
     * @return
     */
    public AbstractModel createTaskTemplate(TrtcParams params);

    /**
     * 删除任务流模板
     * @return
     */
    public AbstractModel deleteTaskTemplate(TrtcParams params);
}
