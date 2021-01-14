package com.example.activemq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Slf4j
@RestController
@SpringBootApplication
public class ActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @GetMapping("/queue/test")
    public String sendQueue() {
        String message = " queue test";
        this.sendMessage(queue, message);
        return "success";
    }

    @GetMapping("/topic/test")
    public String sendTopic() {
        String message = " topic test";
        this.sendMessage(topic, message);
        return "success";
    }

    private void sendMessage(Destination destination, final String message){
        log.info("Send message: {} , From: {}", message, destination);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
