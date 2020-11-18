package com.example.demo.work6;

import com.apple.eawt.Application;
import com.example.demo.DemoApplication;
import com.example.demo.work4.Student;
import com.example.demo.work4.StudentAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
class StudentServiceTest {
    @Autowired
    StudentService studentService;


    @Test
    void selectByPrimaryKey() {
        Student s = studentService.selectByPrimaryKey(1);
        Assert.notNull(s, "Student must not null");
    }

    @Test
    void selectById() {
        Student s = studentService.selectById(1);
        Assert.notNull(s, "Student must not null");
    }

    @Test
    void selectByPrimaryId() {
        Student s = studentService.selectByPrimaryId(1);
        Assert.notNull(s, "Student must not null");
    }
}