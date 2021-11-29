package com.luoyvdi.service;

import com.luoyvdi.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user 用户信息
     * @return 返回null
     */
    public User login(User user);

    /**
     * 查询用户名是否可用
     * @param username 用户名
     * @return 返回true表示用户名已存在不可用,返回force表示不存在可用
     */
    public boolean existsUserName(String username);
}
