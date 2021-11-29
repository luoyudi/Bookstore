package com.luoyvdi.test;

import com.luoyvdi.dao.UserDao;
import com.luoyvdi.dao.impl.UserDaoImpl;
import com.luoyvdi.domain.User;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"test","test","test@qq.com")));
    }
}