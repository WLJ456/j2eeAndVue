package com.wlj.service;

import com.wlj.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 添加用户
     */
    Boolean addUser(User user);

    /**
     * 根据用户名查询用户信息
     */
    User findUserByName(String username);

    User findUserById(Integer id);
    Boolean isExitName(String username);

    /**
     * 更新用户信息
     */
    Boolean updateUserMsg(User user);


}
