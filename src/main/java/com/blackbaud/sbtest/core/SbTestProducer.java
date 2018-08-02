package com.blackbaud.sbtest.core;

import com.blackbaud.azure.servicebus.publisher.JsonMessagePublisher;
import com.blackbaud.sbtest.servicebus.SbtestPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@Order(100)
public class SbTestProducer implements CommandLineRunner {

    @Value("${sbtest.message.count}")
    private Long messageCount;

    @Autowired
    private RandomKeys randomKeys;

    @Autowired
    JsonMessagePublisher publisher;

    List<CompletableFuture> results;

    @Override
    public void run(String... args) throws Exception {
        log.info("SbTestProducer running...");

        for (int i = 0; i <= messageCount; i++) {
            SbtestPayload payload = SbtestPayload.builder()
                    .id(randomKeys.getKeyAsString())
                    .build();
//            results.add(publisher.sendAsync(payload));
            publisher.sendSync(payload);
        }
        log.info("Messages sent, awaiting results");
//        CompletableFuture.allOf(results.toArray(new CompletableFuture[0]));

        log.info("SbTestProducer finished sending");
    }
}
