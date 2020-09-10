package com.zzz.videoserver.model.params.impl;

import com.tencentcloudapi.mps.v20190612.models.AudioTemplateInfo;
import com.tencentcloudapi.mps.v20190612.models.TranscodeTemplate;
import com.tencentcloudapi.mps.v20190612.models.VideoTemplateInfo;
import com.zzz.videoserver.model.params.TrtcParams;
import lombok.Data;

@Data
public class TranscodeTemplateParam extends TrtcParams {
    private String name;
    private String container;
    private VideoTemplateInfo videoTemplate = new VideoTemplateInfo();
    private AudioTemplateInfo audioTemplate = new AudioTemplateInfo();

    public TranscodeTemplateParam(String name, String container) {
        this.name = name;
        this.container = container;
        videoTemplate.setBitrate(0l);
        videoTemplate.setCodec("libx264");
        videoTemplate.setFps(0l);
        videoTemplate.setWidth(0l);
        videoTemplate.setHeight(0l);
        videoTemplate.setResolutionAdaptive("close");

        audioTemplate.setCodec("libfdk_aac");
        audioTemplate.setBitrate(0l);
        audioTemplate.setSampleRate(48000l);
    }
}
