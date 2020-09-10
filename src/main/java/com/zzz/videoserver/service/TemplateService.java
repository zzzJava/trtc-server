package com.zzz.videoserver.service;

import com.tencentcloudapi.common.AbstractModel;
import com.zzz.videoserver.model.params.TrtcParams;

public interface TemplateService {
    /**
     * 获取所有模板
     * @param params
     * @return
     */
    public AbstractModel getAllTemplates(TrtcParams params);

    /**
     * 创建模板
     * @param params
     * @return
     */
    public AbstractModel createTemplate(TrtcParams params);

    /**
     * 删除模板
     * @param params
     * @return
     */
    public AbstractModel deleteTemplate(TrtcParams params);

    /**
     * 修改模板
     * @param params
     * @return
     */
    public AbstractModel modifyTemplate(TrtcParams params);
}
