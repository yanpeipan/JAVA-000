package com.example.demo.work4;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private int id;

    private String name;
}