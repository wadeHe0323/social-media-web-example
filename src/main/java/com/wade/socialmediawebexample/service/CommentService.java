package com.wade.socialmediawebexample.service;

import com.wade.socialmediawebexample.model.Comment;

import java.util.List;

public interface CommentService {

    void createComment(Comment comment);

    List<Comment> getCommentsByPostId(Integer PostId);
}
