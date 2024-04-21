package com.wade.socialmediawebexample.dao.impl;

import com.wade.socialmediawebexample.dao.UserDao;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.rowmapper.MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberRowMapper memberRowMapper;

    @Override
    public void createUser(Member member) {

        String sql = "INSERT INTO user(user_name, password, email, cover_image, biography) VALUES (:username, :password, :email, :coverImage, :biography)";

        Map<String, Object> map = new HashMap<>();
        map.put("username", member.getUsername());
        map.put("password", member.getPassword());
        map.put("email", member.getEmail());
        map.put("coverImage", member.getCoverImage());
        map.put("biography", member.getBiography());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Member findUserByUsername(String username) {

        String sql = "SELECT user_id, user_name, email, password, cover_image, biography FROM user WHERE user_name = :username";

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);

        List<Member> members = namedParameterJdbcTemplate.query(sql, map, memberRowMapper);

        if (!members.isEmpty()) {
            return members.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Member findUserById(Integer userId) {
        String sql = "SELECT user_id, user_name, email, password, cover_image, biography FROM user WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<Member> members = namedParameterJdbcTemplate.query(sql, map, memberRowMapper);

        if (!members.isEmpty()) {
            return members.get(0);
        } else {
            return null;
        }
    }
}
