package com.example.demo.work4;

import com.apple.eawt.Application;
import com.example.demo.DemoApplication;
import com.example.demo.work6.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles
@SpringBootTest(classes = {DemoApplication.class})
class StudentAutoConfigurationTest {

    @Autowired
    private Student student;

    @Test
    void createStudent() {
        log.info("student {}", student);
        Assert.notNull(student.getId(), "student id MUST NOT NULL");
        Assert.isTrue(student.getId() > 0, "student id MUST GT 0");
        Assert.notNull(student.getName(), "student name MUST NOT NULL");
    }
}