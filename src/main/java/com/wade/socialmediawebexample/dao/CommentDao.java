package com.wade.socialmediawebexample.dao;

import com.wade.socialmediawebexample.model.Comment;

import java.util.List;

public interface CommentDao {

    void createComment(Comment comment);

    List<Comment> getCommentsByPostId(Integer postId);

    void deleteCommentsByPostId(Integer postId);
}
