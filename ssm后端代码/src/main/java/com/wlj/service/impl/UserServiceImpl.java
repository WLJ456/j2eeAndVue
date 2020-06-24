package com.wlj.service.impl;

import com.wlj.dao.UserDao;
import com.wlj.domain.User;
import com.wlj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Boolean addUser(User user) {
        return userDao.addUser(user) == 1 ? true : false;
    }

    //通过账号查询，返回用户
    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    //判断该用户是否存在
    @Override
    public Boolean isExitName(String username) {
        return userDao.findUserByName(username) == null ? false : true;
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public Boolean updateUserMsg(User user) {
        System.out.println(userDao.updateUserMsg(user));
        return userDao.updateUserMsg(user) == 1 ? true : false;
    }
}
