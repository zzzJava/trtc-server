package com.zzz.videoserver.model.params.impl;

import com.tencentcloudapi.vod.v20180717.models.AdaptiveDynamicStreamingTaskInput;
import com.tencentcloudapi.vod.v20180717.models.MediaProcessTaskInput;
import com.zzz.videoserver.model.params.TrtcParams;
import lombok.Data;

@Data
public class AdaptiveTranscodeParam extends TrtcParams {
    /**
     * 任务流名称
     */
    private String name;
    private String comment;

    private MediaProcessTaskInput mediaProcessTask = new MediaProcessTaskInput();

    public AdaptiveTranscodeParam(String name) {
        this.name = name;
    }

    public void setAdaptiveDynamicStreamingTaskSet(AdaptiveDynamicStreamingTaskInput[] inputs) {
        this.mediaProcessTask.setAdaptiveDynamicStreamingTaskSet(inputs);
    }

}
