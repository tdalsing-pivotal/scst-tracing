package com.vmware.producer;

import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.cloud.stream.messaging.DirectWithAttributesChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class TracingTestConfig {
    @Bean(name = "output-out-0")
    public SubscribableChannel subscribableChannelWithTracingInterceptor(BindingService bindingService) {
        DirectWithAttributesChannel channel = new DirectWithAttributesChannel();
        channel.setAttribute("type", "output");
        bindingService.bindProducer(channel, "output-out-0", false);

        return channel;
    }
}
