package com.wade.socialmediawebexample.service.impl;

import com.wade.socialmediawebexample.dao.CommentDao;
import com.wade.socialmediawebexample.model.Comment;
import com.wade.socialmediawebexample.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentDao.getCommentsByPostId(postId);
    }
}
