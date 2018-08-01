package com.blackbaud.sbtest.core;

import com.blackbaud.azure.servicebus.publisher.JsonMessagePublisher;
import com.blackbaud.sbtest.servicebus.SbtestPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Order(100)
public class SbTestProducer implements CommandLineRunner {

    @Value("${sbtest.message.count}")
    private Long messageCount;

    @Value("${sbtest.message.batchsize:100}")
    private Long batchSize;

    @Autowired
    private RandomKeys randomKeys;

    @Autowired
    JsonMessagePublisher publisher;

    @Override
    public void run(String... args) throws Exception {
        log.warn("SbTestProducer running...");

        for (int currentBatch = 0; currentBatch < messageCount/batchSize; currentBatch++) {
            List<Object> batch = new ArrayList<>();

            for (int x = 0; x < batchSize; x++) {
                batch.add(SbtestPayload.builder().id(randomKeys.getKey().toString()).build());
            }
            publisher.sendBatchSync(batch);
            log.warn("SbTestProducer sending batch {}", currentBatch);
        }
        log.warn("SbTestProducer finished sending");
    }
}
