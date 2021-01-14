package com.example.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class TopicConsumer {

    @JmsListener(destination="${spring.activemq.topic-name:activemq.topic}")
    public void readActiveQueue(String message) {
        log.info("Read Topic message: {}", message);
    }
}
