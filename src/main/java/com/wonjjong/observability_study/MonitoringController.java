package com.wonjjong.observability_study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController("/monitoring")
@RequiredArgsConstructor
public class MonitoringController {
    private final Executor myThreadPoolExecutor;

    private final Executor mySecondThreadPoolExecutor;

    @GetMapping("/my-thread-pool")
    public String myThreadPool() {
        myThreadPoolExecutor.execute(MonitoringController::sleep);
        return "ok";
    }

    @GetMapping("/my-second-thread-pool")
    public String mySecondThreadPool() {
        mySecondThreadPoolExecutor.execute(MonitoringController::sleep);
        return "ok";
    }

    private static void sleep() {
        try {
            Thread.sleep(1000 * 100); // 100초 sleep
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
