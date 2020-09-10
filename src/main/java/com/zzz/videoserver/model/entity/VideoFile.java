package com.zzz.videoserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoFile {
    private String name;
    private String fileId;
    /**
     * 封面图片链接
     */
    private String coverUrl;
    private FileStatus status;

    public enum FileStatus {
        UPLOADED, TRANSCODING, AVAILABLE, ERROR;
    }
}
