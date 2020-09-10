package com.zzz.videoserver.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.trtc.v20190722.TrtcClient;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.zzz.videoserver.config.properties.AppProperties;
import com.zzz.videoserver.config.properties.SecretProperties;
import com.zzz.videoserver.config.properties.TRTCProperties;
import com.zzz.videoserver.config.properties.VodProperties;
import com.zzz.videoserver.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecretProperties.class, AppProperties.class, TRTCProperties.class, VodProperties.class})
public class TencentConfig {
    @Bean
    public SignUtil signUtil(AppProperties properties,  @Autowired SecretProperties secretProperties) {
        SignUtil signUtil = new SignUtil(properties.getSdkAppId(), properties.getAppKey(),
                secretProperties.getSecretId(), secretProperties.getSecretKey());
        return signUtil;
    }

    @Bean
    public VodClient vodClient(SecretProperties properties, @Autowired VodProperties vodProperties) {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(vodProperties.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new VodClient(cred, vodProperties.getRegion(), clientProfile);
    }

    @Bean
    public TrtcClient trtcClient(SecretProperties properties, @Autowired TRTCProperties trtcProperties) {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(trtcProperties.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new TrtcClient(cred, trtcProperties.getRegion(), clientProfile);
    }
}
