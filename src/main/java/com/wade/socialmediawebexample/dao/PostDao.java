package com.wade.socialmediawebexample.dao;

import com.wade.socialmediawebexample.dto.PostRequest;
import com.wade.socialmediawebexample.model.Post;

import java.util.List;

public interface PostDao {

    void createPost(Post post);

    List<Post> getPosts();

    List<Post> getPostsByUserId(Integer userId);

    void updatePost(Integer postId, PostRequest postRequest);

    void deletePostById(Integer postId);

    Post getPostById(Integer postId);
}
