package com.blackbaud.sbtest.servicebus;

import com.blackbaud.azure.servicebus.config.ServiceBusConsumerConfig;
import com.blackbaud.azure.servicebus.config.ServiceBusPublisherConfig;
import com.blackbaud.azure.servicebus.consumer.ServiceBusConsumer;
import com.blackbaud.azure.servicebus.consumer.ServiceBusConsumerBuilder;
import com.blackbaud.azure.servicebus.publisher.JsonMessagePublisher;
import com.blackbaud.azure.servicebus.publisher.ServiceBusPublisherBuilder;
import com.blackbaud.azure.servicebus.publisher.SessionIdResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceBusConsumerConfig.class, ServiceBusPublisherConfig.class})
@EnableConfigurationProperties(SbtestServiceBusProperties.class)
public class ServiceBusConfig {

    @Bean
    public JsonMessagePublisher sbtestPublisher(
            ServiceBusPublisherBuilder.Factory serviceBusPublisherFactory,
            SbtestServiceBusProperties serviceBusProperties) {
        SessionIdResolver<SbtestPayload> sessionIdResolver = SbtestPayload::getId;
        return serviceBusPublisherFactory.create()
                .supportsSessions(sessionIdResolver)
                .buildJsonPublisher(serviceBusProperties);
    }

    @Bean
    public SbtestMessageHandler sbtestMessageHandler() {
        return new SbtestMessageHandler();
    }

    @Bean
    public ServiceBusConsumer sbtestConsumer(
            ServiceBusConsumerBuilder.Factory serviceBusConsumerFactory,
            SbtestMessageHandler sbtestMessageHandler,
            SbtestServiceBusProperties serviceBusProperties) {
        return serviceBusConsumerFactory.create()
                .dataSyncTopicServiceBus(serviceBusProperties)
                .jsonMessageHandler(sbtestMessageHandler, SbtestPayload.class)
                .build();
    }

}
