package com.zzz.videoserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.vod.v20180717.models.FileDeleteTask;
import com.tencentcloudapi.vod.v20180717.models.FileUploadTask;
import com.tencentcloudapi.vod.v20180717.models.ProcedureTask;
import com.zzz.videoserver.config.properties.VodProperties;
import com.zzz.videoserver.model.entity.VideoFile;
import com.zzz.videoserver.model.params.TrtcParams;
import com.zzz.videoserver.model.params.TrtcParamsAdapter;
import com.zzz.videoserver.service.EventHandleService;
import com.zzz.videoserver.service.MediaProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯云点播回调接口，用于事件通知
 * 采用普通回调
 */
@RestController
@Slf4j
public class CallbackController {
    @Autowired
    private EventHandleService service;
    @Autowired
    private MediaProcessService processService;
    @Autowired
    private VodProperties vodProperties;

    @PostMapping(path = "callback")
    public void callback(@RequestBody String info) {
        JSONObject jsonObject = JSON.parseObject(info);
        String eventType = (String)jsonObject.get("EventType");
        switch (eventType) {
            case "NewFileUpload":
                VideoFile file =
                        service.uploadFile(JSON.parseObject(jsonObject.get("FileUploadEvent").toString(), FileUploadTask.class));
                adaptiveTranscode(file);
                break;
            case "FileDeleted":
                service.deleteFiles(JSON.parseObject(jsonObject.get("FileDeleteEvent").toString(), FileDeleteTask.class));
                break;
            case "ProcedureStateChanged":
                service.changeProcedureStatus(JSON.parseObject(jsonObject.get("ProcedureStateChangeEvent").toString(), ProcedureTask.class));
                break;
            default:
                log.info("其他事件");
        }
    }

    private void adaptiveTranscode(VideoFile file) {
        Map<String, Object> map = new HashMap<>();
        map.put("FileId", file.getFileId());
        map.put("ProcedureName", vodProperties.getAdaptiveTranscodeTask());
        TrtcParams params = new TrtcParamsAdapter(map);
        processService.processByProcedure(params);
    }
}
