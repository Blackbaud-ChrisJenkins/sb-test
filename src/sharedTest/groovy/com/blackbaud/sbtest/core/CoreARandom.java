package com.blackbaud.sbtest.core;

import com.blackbaud.testsupport.RandomGenerator;
import com.blackbaud.sbtest.servicebus.ServiceBusClientRandomBuilderSupport;
import lombok.experimental.Delegate;

public class CoreARandom {

    public static final CoreARandom aRandom = new CoreARandom();

    @Delegate
    public CoreRandomBuilderSupport coreRandomBuilderSupport = new CoreRandomBuilderSupport();
    @Delegate
    private ServiceBusClientRandomBuilderSupport serviceBusClientRandomBuilderSupport = new ServiceBusClientRandomBuilderSupport();
    @Delegate
    private RandomGenerator randomGenerator = new RandomGenerator();

}
