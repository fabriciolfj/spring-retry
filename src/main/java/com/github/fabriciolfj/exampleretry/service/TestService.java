package com.github.fabriciolfj.exampleretry.service;

import com.github.fabriciolfj.exampleretry.dto.Test;
import com.github.fabriciolfj.exampleretry.repository.TestRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
    private int count;
    private long time = System.currentTimeMillis();

    @Autowired
    private TestRepository repository;

    @Retry(name = "retryApi", fallbackMethod = "fallBackDefault")
    public Test execute() {
        if (count < 3) {
            count++;
            throw new RuntimeException();
        }

        var timeResult = (System.currentTimeMillis() - time) / 1000;
        LOGGER.info("Quantidade de retentativas: {}, time {}", count, timeResult);
        return new Test("Result " + count);
    }

    public Test fallBackDefault(Exception e) {
        return new Test("fallback");
    }

    public Test process() {
        return repository.find()
                .map(e -> new Test("ok repo"))
                .orElseThrow(() -> {
                    System.out.println("caiu aqui");
                    return new RuntimeException("Falha");
                });
    }
}
