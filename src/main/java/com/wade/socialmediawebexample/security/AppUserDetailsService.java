package com.wade.socialmediawebexample.security;

import com.wade.socialmediawebexample.dao.UserDao;
import com.wade.socialmediawebexample.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = userDao.findUserByUsername(username);

        if (member == null) {
            throw new UsernameNotFoundException("User not found! username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(member.getUsername(), member.getPassword(), authorities);
    }
}
