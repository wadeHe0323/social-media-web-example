package com.wade.socialmediawebexample.service;

import com.wade.socialmediawebexample.dto.PostInfo;
import com.wade.socialmediawebexample.dto.PostRequest;
import com.wade.socialmediawebexample.model.Post;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    List<PostInfo> getPosts(String myName);

    List<PostInfo> getPostsByUserName(String myName, String username);

    void updatePost(Integer postId, PostRequest postRequest);

    void deletePostById(Integer postId);

    Post getPostById(Integer postId);
}
