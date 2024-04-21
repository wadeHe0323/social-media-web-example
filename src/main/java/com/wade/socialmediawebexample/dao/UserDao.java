package com.wade.socialmediawebexample.dao;

import com.wade.socialmediawebexample.model.Member;

public interface UserDao{

    void createUser(Member member);
    Member findUserByUsername(String username);
    Member findUserById(Integer userId);
}
