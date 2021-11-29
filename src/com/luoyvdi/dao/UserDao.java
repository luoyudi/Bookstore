package com.luoyvdi.dao;

import com.luoyvdi.domain.User;

public interface UserDao {

    /**
     * 查询用户是否存在
     * @param username 用户名
     * @return 返回null用户不存在,反之则存在
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户是否存在
     * @param username 用户名
     * @param password 密码
     * @return 返回null说明用户名或密码错误,反之则成功
     */
    public User queryUserByUsernameAndPassword (String username,String password);

    /**
     * 保存用户信息
     * @param user 用户JavaBean对象
     * @return 返回-1失败,返回1成功
     */
    public int saveUser(User user);
}
