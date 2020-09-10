package com.zzz.videoserver.model.params.impl;

import com.tencentcloudapi.mps.v20190612.models.TranscodeTemplate;
import com.tencentcloudapi.trtc.v20190722.models.EncodeParams;
import com.tencentcloudapi.trtc.v20190722.models.LayoutParams;
import com.tencentcloudapi.trtc.v20190722.models.OutputParams;
import com.zzz.videoserver.model.params.TrtcParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class TransCodeParam extends TrtcParams {
    /**
     * 混流的房间号
     */
    private Integer RoomId;
    private OutputParams OutputParams = new OutputParams();
    private EncodeParams EncodeParams = new EncodeParams();
    private LayoutParams LayoutParams = new LayoutParams();
    private long SdkAppId;

    public TransCodeParam(Integer roomId, String recordId, long SdkAppId) {
        this.setSdkAppId(SdkAppId);
        this.RoomId = roomId;
        LayoutParams.setTemplate(1l); // 默认设置为九宫格模板
        if (recordId != null) OutputParams.setRecordId(recordId);
        EncodeParams.setAudioSampleRate(48000l);
        EncodeParams.setAudioBitrate(64l);
        EncodeParams.setAudioChannels(2l);
        EncodeParams.setVideoWidth(600l);
        EncodeParams.setVideoHeight(400l);
        EncodeParams.setVideoBitrate(3200l);
        EncodeParams.setVideoFramerate(15l);
        EncodeParams.setVideoGop(3l);
        OutputParams.setStreamId("Stream-" + System.currentTimeMillis());
    }

    private static class TranscodeTemplateFactory {
    }

}
