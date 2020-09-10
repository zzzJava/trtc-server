package com.zzz.videoserver.service;

import com.tencentcloudapi.vod.v20180717.models.FileDeleteTask;
import com.tencentcloudapi.vod.v20180717.models.FileUploadTask;
import com.tencentcloudapi.vod.v20180717.models.ProcedureTask;
import com.zzz.videoserver.model.entity.VideoFile;

/**
 * 回调事件处理接口
 */
public interface EventHandleService {
    /**
     * 处理文件上传回调
     * @param task
     */
    public VideoFile uploadFile(FileUploadTask task);

    /**
     * 处理文件删除回调
     * @param fileDeleteEvent
     */
    public void deleteFiles(FileDeleteTask fileDeleteEvent);

    /**
     * 处理任务流状态改变回调
     * @param procedureStateChangeEvent
     */
    public void changeProcedureStatus(ProcedureTask procedureStateChangeEvent);

}
