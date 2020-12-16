package com.vmware.consumer;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Consumer;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @Bean
    public Consumer<Message<Map<String, Object>>> consumer() {
        log.info("consumer");
        return message -> log.info("consumer: message = {}", message);
    }
}
