package com.zzz.videoserver.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tencent.trtc")
@Data
public class TRTCProperties {
    private String region;
    private String endpoint;
}
