package com.blackbaud.sbtest.servicebus;

import com.blackbaud.azure.servicebus.consumer.ServiceBusMessage;
import com.blackbaud.azure.servicebus.consumer.handlers.MessageHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SbtestMessageHandler implements MessageHandler<SbtestPayload> {

    @Override
    public void process(ServiceBusMessage<SbtestPayload> message) {
        SbtestPayload payload = message.getPayload();
    }

}
