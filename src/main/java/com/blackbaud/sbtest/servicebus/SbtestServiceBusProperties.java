package com.blackbaud.sbtest.servicebus;

import com.blackbaud.azure.servicebus.config.ServiceBusProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "servicebus.sbtest")
public class SbtestServiceBusProperties extends ServiceBusProperties {
}