package com.blackbaud.sbtest.servicebus;

import com.blackbaud.azure.servicebus.consumer.ServiceBusMessage;
import com.blackbaud.azure.servicebus.consumer.handlers.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class SbtestMessageHandler implements MessageHandler<SbtestPayload> {

    @Value("${sbtest.message.count}")
    private Long expectedMessages;


    private AtomicLong messageTotal = new AtomicLong();

    private LocalDateTime startTime;

    @Override
    public void process(ServiceBusMessage<SbtestPayload> message) {
        if (messageTotal.get() == 0) {
            startTime = LocalDateTime.now();
        }

        if (messageTotal.incrementAndGet() >= expectedMessages) {
            LocalDateTime endTime = LocalDateTime.now();
            log.info("Start time: {}", startTime);
            log.info("End time: {}", endTime);
            Long elapsedSeconds = ChronoUnit.SECONDS.between(startTime, endTime);
            log.info("Elapsed time: {} seconds", elapsedSeconds);
        } else {
            log.debug("Consumed messages: {}", messageTotal);
        }

    }

}
