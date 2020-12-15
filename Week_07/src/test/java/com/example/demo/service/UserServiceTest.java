package com.example.demo.service;

import com.example.demo.model.User;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private Faker faker = new Faker();

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private ExecutorService executorService;

    @Test
    void insert() {
    }

    @Test
    void batchInsert() {
        try (
                SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        ) {
            for (int i = 0; i < 10000/5; i++) {
                userService.batchInsert(fakeUsers());
                sqlSession.commit();
            }
        }
    }

    List<User> fakeUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            users.add(fakeUser());
        }
        return users;
    }


    List<User> fakeUsers(int n) {
        List<User> users = new ArrayList<>();
        List<CompletableFuture<User>> completableFutureList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            completableFutureList.add(CompletableFuture.supplyAsync(this::fakeUser, executorService));
        }
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).thenAccept(ignore -> {
            users.addAll(completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        }).join();
        return users;
    }

    User fakeUser() {
        return User.builder()
                .name(faker.name().name())
                .realName(faker.name().fullName())
                .addressId(faker.number().randomNumber())
                .status((byte) faker.number().numberBetween(0, 10))
                .sex((byte) faker.number().numberBetween(0, 3))
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }
}