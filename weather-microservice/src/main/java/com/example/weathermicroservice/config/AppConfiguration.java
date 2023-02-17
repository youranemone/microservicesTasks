package com.example.weathermicroservice.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class AppConfiguration {
    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean("defaultCacheManager")
    public CacheManager defaultCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(){
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).maximumSize(100).build().asMap(), false);
            }
        };
        return cacheManager;
    }


}
