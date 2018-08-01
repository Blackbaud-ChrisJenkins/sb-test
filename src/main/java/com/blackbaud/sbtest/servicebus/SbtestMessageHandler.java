package com.blackbaud.sbtest.servicebus;

import com.blackbaud.azure.servicebus.consumer.ServiceBusMessage;
import com.blackbaud.azure.servicebus.consumer.handlers.MessageHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SbtestMessageHandler implements MessageHandler<SbtestPayload> {

    private Long messageTotal = 0L;

    @Override
    public void process(ServiceBusMessage<SbtestPayload> message) {
        messageTotal++;
        SbtestPayload payload = message.getPayload();
        log.warn("Messages consumed: {}", messageTotal);
    }

}
