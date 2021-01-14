package com.example.activemq.consumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class QueueConsumer {

    @JmsListener(destination = "${spring.activemq.queue-name:activemq.queue}")
    public void readActiveMQ(String message) {
        log.info("Read Queue message: {}", message);
    }
}
