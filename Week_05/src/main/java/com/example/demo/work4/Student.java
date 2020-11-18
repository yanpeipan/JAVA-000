package com.example.demo.work4;

import lombok.*;

@Data
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;

    private String name;
}