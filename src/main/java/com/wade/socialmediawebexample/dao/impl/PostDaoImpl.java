package com.wade.socialmediawebexample.dao.impl;

import com.wade.socialmediawebexample.dao.PostDao;
import com.wade.socialmediawebexample.dto.PostRequest;
import com.wade.socialmediawebexample.model.Post;
import com.wade.socialmediawebexample.rowmapper.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostDaoImpl implements PostDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PostRowMapper postRowMapper;

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO post(user_id, content, create_at) VALUES (:userId, :content, :createAt)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", post.getUserId());
        map.put("content", post.getContent());
        Date now = new Date();
        map.put("createAt", now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Post> getPosts() {
        String sql = "SELECT post_id, user_id, content, create_at FROM post ORDER BY create_at DESC";

        List<Post> posts = namedParameterJdbcTemplate.query(sql, new HashMap<>(), postRowMapper);

        if (!posts.isEmpty()) {
            return posts;
        } else {
            return null;
        }
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        String sql = "SELECT post_id, user_id, content, create_at FROM post WHERE user_id = :userId ORDER BY create_at DESC";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<Post> posts = namedParameterJdbcTemplate.query(sql, map, postRowMapper);

        if (!posts.isEmpty()) {
            return posts;
        } else {
            return null;
        }
    }

    @Override
    public void updatePost(Integer postId, PostRequest postRequest) {
        String sql = "UPDATE post SET content = :content, create_at = :createAt WHERE post_id = :postId";

        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("content", postRequest.getContent());
        map.put("createAt", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deletePostById(Integer postId) {
        String sql = "DELETE FROM post WHERE post_id = :postId";

        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Post getPostById(Integer postId) {
        String sql = "SELECT post_id, user_id, content, create_at FROM post WHERE post_id = :postId";

        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        List<Post> posts = namedParameterJdbcTemplate.query(sql, map, postRowMapper);

        if (!posts.isEmpty()) {
            return posts.get(0);
        } else {
            return null;
        }
    }
}
