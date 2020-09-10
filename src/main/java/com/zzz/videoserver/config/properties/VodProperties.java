package com.zzz.videoserver.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tencent.vod")
@Data
public class VodProperties {
    private String region;
    private String endpoint;
    /**
     * 转自适应码流任务流名称
     * 系统初始化时会检查对应任务流是否存在，
     * 不存在则自动创建
     */
    private String adaptiveTranscodeTask;
}
