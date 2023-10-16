package com.vmware.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class ProducerTest {

    RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Test
    void post() {
        send(Map.of("id", "id-1", "name", "name-1", "count", 1, "amount", 11.1));
        send(Map.of("id", "id-2", "name", "name-2", "count", 2, "amount", 22.2));
        send(Map.of("id", "id-3", "name", "name-3", "count", 3, "amount", 33.3, "error", true));
    }

    private void send(Map<String, Object> data) {
        restTemplate.postForObject("http://localhost:8301/", data, Void.class);
        log.info("send: data = {}", data);
    }
}
