package com.wade.socialmediawebexample.service.impl;

import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.dao.UserDao;
import com.wade.socialmediawebexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(Member member) {
        userDao.createUser(member);
    }

    @Override
    public Member getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Member getUserById(Integer userId) {
        return userDao.findUserById(userId);
    }
}
