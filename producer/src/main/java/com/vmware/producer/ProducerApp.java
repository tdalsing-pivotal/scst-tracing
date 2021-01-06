package com.vmware.producer;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.instrument.messaging.TracingChannelInterceptor;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.DirectWithAttributesChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootApplication
@RestController
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProducerApp {

    StreamBridge bridge;
    BindingService bindingService;

    public ProducerApp(StreamBridge bridge, BindingService bindingService) {
        this.bridge = bridge;
        this.bindingService = bindingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Bean(name = "output-out-0")
    public MessageChannel outputChannel(TracingChannelInterceptor interceptor) {
        log.info("outputChannel");
        DirectWithAttributesChannel channel = new DirectWithAttributesChannel();
        channel.setAttribute("type", "output");
        channel.addInterceptor(interceptor);
        bindingService.bindProducer(channel, "output-out-0", false);
        return channel;
    }

    @PostMapping("/")
    @ResponseStatus(CREATED)
    public void post(@RequestBody Map<String, Object> data) {
        log.info("post: data = {}", data);
        bridge.send("output-out-0", data);
    }
}
