package com.blackbaud.sbtest;

import org.springframework.context.annotation.Import;
import com.blackbaud.sbtest.servicebus.ServiceBusConfig;
import com.blackbaud.boot.config.CommonSpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.blackbaud.sbtest.core, com.blackbaud.sbtest.resources")
@Import(ServiceBusConfig.class)
public class SbTest extends CommonSpringConfig {

    public static void main(String[] args) {
        SpringApplication.run(SbTest.class, args);
    }

}
