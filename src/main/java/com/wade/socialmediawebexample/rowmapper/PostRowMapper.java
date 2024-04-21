package com.wade.socialmediawebexample.rowmapper;

import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.model.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();

        post.setPostId(rs.getInt("post_id"));
        post.setUserId(rs.getInt("user_id"));
        post.setContent(rs.getString("content"));
        post.setCreatedAt(rs.getTimestamp("create_at"));

        return post;
    }
}
