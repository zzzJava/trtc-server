package com.zzz.videoserver.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tencent.app")
@Data
public class AppProperties {
    private long sdkAppId;
    private String appKey;
}
