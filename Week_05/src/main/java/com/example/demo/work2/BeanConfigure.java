package com.example.demo.work2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {
    @Bean
    public Bean2 initBean2() {
        return new Bean2();
    }
}
