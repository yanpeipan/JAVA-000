package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {


    @Bean
    public ExecutorService getThreadPool() {
        return Executors.newFixedThreadPool(8);
    }

}
