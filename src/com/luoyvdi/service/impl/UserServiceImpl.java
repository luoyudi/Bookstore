package com.luoyvdi.service.impl;

import com.luoyvdi.dao.UserDao;
import com.luoyvdi.dao.impl.UserDaoImpl;
import com.luoyvdi.domain.User;
import com.luoyvdi.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUserName(String username) {
        if (userDao.queryUserByUsername(username) == null) {
//            等于null没查到,可用
            return false;
        }
        return true;
    }
}
