package com.zzz.videoserver.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
{
    @Override
    @Bean(name = "simpleCacheManger")
    public CacheManager cacheManager() {
        SimpleCacheManager scm = new SimpleCacheManager();
        // 初始化需要使用的Cache组件
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("apiSign"));
        caches.add(new ConcurrentMapCache("uploadSign"));
        caches.add(new ConcurrentMapCache("videoFileStatus"));
        scm.setCaches(caches);
        return scm;
    }
}
