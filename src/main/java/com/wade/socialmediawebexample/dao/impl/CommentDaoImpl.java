package com.wade.socialmediawebexample.dao.impl;

import com.wade.socialmediawebexample.dao.CommentDao;
import com.wade.socialmediawebexample.model.Comment;
import com.wade.socialmediawebexample.rowmapper.CommentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CommentRowMapper commentRowMapper;

    @Override
    public void createComment(Comment comment) {
        String sql = "INSERT INTO comment(user_id, post_id, content, create_at) VALUES (:userId, :postId, :content, :createAt)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", comment.getUserId());
        map.put("postId", comment.getPostId());
        map.put("content", comment.getContent());
        Date now = new Date();
        map.put("createAt", now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        String sql = "SELECT comment_id, user_id, post_id, content, create_at FROM comment WHERE post_id = :postId";

        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        List<Comment> comments = namedParameterJdbcTemplate.query(sql, map, commentRowMapper);

        if (!comments.isEmpty()) {
            return comments;
        } else {
            return null;
        }
    }

    @Override
    public void deleteCommentsByPostId(Integer postId) {
        String sql = "DELETE FROM comment WHERE post_id = :postId";

        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
