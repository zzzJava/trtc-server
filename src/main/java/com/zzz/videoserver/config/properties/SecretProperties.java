package com.zzz.videoserver.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tencent.secret")
@Data
public class SecretProperties {
    private String secretId;
    private String secretKey;
}
