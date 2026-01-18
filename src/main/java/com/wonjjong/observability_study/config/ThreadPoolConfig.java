package com.wonjjong.observability_study.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public Executor myThreadPoolExecutor(MeterRegistry meterRegistry) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(1000);
        executor.setThreadNamePrefix("my-thread-pool-");
        executor.initialize();

        return ExecutorServiceMetrics.monitor(meterRegistry, executor.getThreadPoolExecutor(), "myThreadPool");
    }
//
//    @Bean
//    public void myThreadPoolMetricConfig(ThreadPoolTaskExecutor myThreadPoolExecutor, MeterRegistry meterRegistry) {
//        Gauge.builder(
//                "myThreadPool.active.count",
//                myThreadPoolExecutor,
//                ThreadPoolTaskExecutor::getActiveCount
//        ).register(meterRegistry);
//
//        Gauge.builder(
//                "myThreadPool.queue.size",
//                myThreadPoolExecutor,
//                ThreadPoolTaskExecutor::getQueueSize
//        ).register(meterRegistry);
//
//
//        Gauge.builder(
//                "myThreadPool.completed.task.count",
//                myThreadPoolExecutor,
//                executor -> executor.getThreadPoolExecutor().getCompletedTaskCount()
//        ).register(meterRegistry);
//    }
}
