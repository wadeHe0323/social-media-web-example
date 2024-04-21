package com.wade.socialmediawebexample.rowmapper;

import com.wade.socialmediawebexample.model.Member;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

        Member member = new Member();

        member.setUserId(rs.getInt("user_id"));
        member.setUsername(rs.getString("user_name"));
        member.setPassword(rs.getString("password"));
        member.setEmail(rs.getString("email"));
        member.setCoverImage(rs.getString("cover_image"));
        member.setBiography(rs.getString("biography"));

        return member;
    }
}
