package com.wade.socialmediawebexample.service;

import com.wade.socialmediawebexample.model.Member;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void add(Member member);

    Member getUserByUsername(String username);

    Member getUserById(Integer userId);
}
