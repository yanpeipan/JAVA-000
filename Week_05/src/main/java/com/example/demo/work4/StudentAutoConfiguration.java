package com.example.demo.work4;

import com.example.demo.work6.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.sql.SQLException;

@Slf4j
@EnableConfigurationProperties({StudentProperties.class})
@Import(StudentService.class)
public class StudentAutoConfiguration {

    @Autowired
    private StudentProperties studentProperties;

    @Bean
    @ConditionalOnMissingBean
    public Student createStudent(StudentProperties properties) throws SQLException {
        Student student = new Student();
        BeanUtils.copyProperties(properties, student);
        return student;
    }
}
