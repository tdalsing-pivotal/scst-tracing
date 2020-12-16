package com.vmware.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
class ProducerAppTest {

    RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setUp() {
    }

    @Disabled
    @NewSpan("post")
    @Test
    void post() {
        Map<String, Object> data = new HashMap<>();

        data.put("id", "id-1");
        data.put("name", "name-1");
        data.put("count", 1);
        data.put("amount", 123.45);

        log.info("post: data = {}", data);
        restTemplate.postForObject("http://localhost:8301/", data, Void.class);
    }
}