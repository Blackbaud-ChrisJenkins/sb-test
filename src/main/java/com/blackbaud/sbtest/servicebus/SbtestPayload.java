package com.blackbaud.sbtest.servicebus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SbtestPayload {

    private String id;

    @Builder.Default()
    private String content = "nothing to see here";

}
