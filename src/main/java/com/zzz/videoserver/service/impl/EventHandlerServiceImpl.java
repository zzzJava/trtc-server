package com.zzz.videoserver.service.impl;

import com.tencentcloudapi.vod.v20180717.models.FileDeleteTask;
import com.tencentcloudapi.vod.v20180717.models.FileUploadTask;
import com.tencentcloudapi.vod.v20180717.models.MediaProcessTaskResult;
import com.tencentcloudapi.vod.v20180717.models.ProcedureTask;
import com.zzz.videoserver.model.entity.VideoFile;
import com.zzz.videoserver.service.EventHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class EventHandlerServiceImpl implements EventHandleService {
    @Override
    public VideoFile uploadFile(FileUploadTask task) {
        String fileId = task.getFileId();
        String coverUrl = task.getMediaBasicInfo().getCoverUrl();
        String name = task.getMediaBasicInfo().getName();
        VideoFile.FileStatus status = VideoFile.FileStatus.UPLOADED;
        VideoFile file = new VideoFile(name, fileId, coverUrl, status);
        // TODO: 存入数据库
        log.info("新视频上传", file);
        return file;
    }

    @Override
    public void deleteFiles(FileDeleteTask fileDeleteEvent) {
        fileDeleteEvent.getFileIdSet();
        log.info("删除文件");
    }

    @Override
    public void changeProcedureStatus(ProcedureTask procedureStateChangeEvent) {
        MediaProcessTaskResult[] mediaProcessResultSet = procedureStateChangeEvent.getMediaProcessResultSet();
        Object[] arr = Arrays.stream(mediaProcessResultSet)
                .filter(result -> "AdaptiveDynamicStreaming".equals(result.getType()))
                .toArray();
        if (arr.length < 1) {
            return; // 没有转自适应码流任务，直接返回
        }

        String fileId = procedureStateChangeEvent.getFileId();
        Long errCode = procedureStateChangeEvent.getErrCode();
        String fileName = procedureStateChangeEvent.getFileName();
        VideoFile file = new VideoFile(fileName, fileId, "", VideoFile.FileStatus.TRANSCODING);

        MediaProcessTaskResult adaptive = (MediaProcessTaskResult)arr[0];
        Long transcodeErrorCode = adaptive.getAdaptiveDynamicStreamingTask().getErrCode();
        String transcodeStatus = adaptive.getAdaptiveDynamicStreamingTask().getStatus();
        if ("FAIL".equals(transcodeStatus)) {
            file.setStatus(VideoFile.FileStatus.ERROR);
        } else if ("SUCCESS".equals(transcodeStatus)) {
            file.setStatus(VideoFile.FileStatus.AVAILABLE);
        }
        // TODO: 存入数据库
        log.info("视频转码完成", file);
    }
}
