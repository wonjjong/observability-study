package com.wonjjong.observability_study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CacheConfig implements CachingConfigurer {
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

    @Slf4j
    private static class CustomCacheErrorHandler implements CacheErrorHandler {

        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
            log.error("Redis 캐시 조회 실패 - cache: {}, key: {}, error: {}. DB에서 조회합니다.",
                    cache.getName(), key, exception.getMessage());
        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
            log.error("Redis 캐시 저장 실패 - cache: {}, key: {}, error: {}",
                    cache.getName(), key, exception.getMessage());
            throw exception;
        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
            log.error("Redis 캐시 삭제 실패 - cache: {}, key: {}, error: {}",
                    cache.getName(), key, exception.getMessage());
            throw exception;
        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {
            log.error("Redis 캐시 전체 삭제 실패 - cache: {}, error: {}",
                    cache.getName(), exception.getMessage());
            throw exception;
        }
    }
}
