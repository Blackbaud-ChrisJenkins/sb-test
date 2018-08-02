package com.blackbaud.sbtest.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
public class RandomKeys {

    @Value("${sbtest.numberofkeys}")
    private Integer numberOfKeys;

    private List<UUID> keys = new ArrayList<>();

    Random rand = new Random();

    @PostConstruct
    public void init() {
        for (int i = 0; i < numberOfKeys; i++) {
            keys.add(UUID.randomUUID());
        }
        log.warn("RandomKeys seeded with " + numberOfKeys + " keys");
    }

    public UUID getKey() {
        int index = rand.nextInt(numberOfKeys);
        return keys.get(index);
    }

    public String getKeyAsString() {
        return getKey().toString();
    }
}
