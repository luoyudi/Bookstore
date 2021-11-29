package com.luoyvdi.test;

import com.luoyvdi.domain.User;
import com.luoyvdi.service.UserService;
import com.luoyvdi.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"test1","test1","test1@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"test1","123",null)));
    }

    @Test
    public void existsUserName() {
        System.out.println(userService.existsUserName("admin"));
    }
}