package com.zzz.videoserver.config;

import com.zzz.videoserver.config.properties.AppProperties;
import com.zzz.videoserver.config.properties.SecretProperties;
import com.zzz.videoserver.utils.SignUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecretProperties.class, AppProperties.class})
public class TencentConfig {
    @Bean
    public SignUtil signUtil(AppProperties properties) {
        SignUtil signUtil = new SignUtil(properties.getSdkAppId(), properties.getAppKey());
        return signUtil;
    }
}
