package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanpeipan
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long uid) {
        return userService.selectByPrimaryKey(uid);
    }

    @PostMapping
    public Object create(@RequestBody User user) {
        return userService.insertSelective(user);
    }
}
