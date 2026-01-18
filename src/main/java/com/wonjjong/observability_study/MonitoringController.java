package com.wonjjong.observability_study;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController("/monitoring")
@RequiredArgsConstructor
public class MonitoringController {
    private final Executor myThreadPoolExecutor;

    @GetMapping("/my-thread-pool")
    public String myThreadPool() {
        myThreadPoolExecutor.execute(
                () -> {
                    try {
                        Thread.sleep(1000 * 100); // 100초 sleep
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        return "ok";
    }
}
