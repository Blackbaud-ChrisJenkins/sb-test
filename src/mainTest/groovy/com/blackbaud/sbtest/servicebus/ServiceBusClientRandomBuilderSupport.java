package com.blackbaud.sbtest.servicebus;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceBusClientRandomBuilderSupport {


    public RandomSbtestPayloadBuilder sbtestPayload() {
        return new RandomSbtestPayloadBuilder();
    }

}
