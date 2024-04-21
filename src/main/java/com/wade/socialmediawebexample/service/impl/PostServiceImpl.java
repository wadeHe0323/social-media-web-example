package com.wade.socialmediawebexample.service.impl;

import com.wade.socialmediawebexample.dao.CommentDao;
import com.wade.socialmediawebexample.dao.PostDao;
import com.wade.socialmediawebexample.dao.UserDao;
import com.wade.socialmediawebexample.dto.PostInfo;
import com.wade.socialmediawebexample.dto.PostRequest;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.model.Post;
import com.wade.socialmediawebexample.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public void createPost(Post post) {
        postDao.createPost(post);
    }

    @Override
    public List<PostInfo> getPosts(String myName) {

        List<Post> posts = postDao.getPosts();

        List<PostInfo> postInfoList = new ArrayList<>();

        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
                PostInfo postInfo = new PostInfo();

                postInfo.setPost(post);

                Member member = userDao.findUserById(post.getUserId());
                postInfo.setMember(member);

                if (myName.equals(member.getUsername())) {
                    postInfo.setMyPost(true);
                } else {
                    postInfo.setMyPost(false);
                }

                postInfo.setComments(commentDao.getCommentsByPostId(post.getPostId()));

                postInfoList.add(postInfo);
            }
        }
        return postInfoList;
    }

    @Override
    public List<PostInfo> getPostsByUserName(String myName, String username) {
        Member member = userDao.findUserByUsername(username);
        List<PostInfo> postInfoList = new ArrayList<>();
        if (member != null) {
            List<Post> posts = postDao.getPostsByUserId(member.getUserId());
            if (posts != null && !posts.isEmpty()) {
                for (Post post : posts) {
                    PostInfo postInfo = new PostInfo();

                    postInfo.setPost(post);

                    Member user = userDao.findUserById(post.getUserId());
                    postInfo.setMember(user);

                    if (myName.equals(user.getUsername())) {
                        postInfo.setMyPost(true);
                    } else {
                        postInfo.setMyPost(false);
                    }

                    postInfo.setComments(commentDao.getCommentsByPostId(post.getPostId()));

                    postInfoList.add(postInfo);
                }
            }
        }
        return postInfoList;
    }

    @Override
    public void updatePost(Integer postId, PostRequest postRequest) {
        postDao.updatePost(postId, postRequest);
    }

    @Override
    @Transactional
    public void deletePostById(Integer postId) {
        commentDao.deleteCommentsByPostId(postId);
        postDao.deletePostById(postId);
    }

    @Override
    public Post getPostById(Integer postId) {
        return postDao.getPostById(postId);
    }
}
