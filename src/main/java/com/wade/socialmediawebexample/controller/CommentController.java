package com.wade.socialmediawebexample.controller;

import com.wade.socialmediawebexample.dto.CommentInfo;
import com.wade.socialmediawebexample.model.Comment;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.model.Post;
import com.wade.socialmediawebexample.service.CommentService;
import com.wade.socialmediawebexample.service.PostService;
import com.wade.socialmediawebexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts/{postId}/comments")
    public String getComment(@PathVariable Integer postId, Model model){
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        Member user = userService.getUserById(post.getUserId());
        model.addAttribute("user", user);
        List<Comment> commentList = commentService.getCommentsByPostId(postId);
        List<CommentInfo> commentInfoList = new ArrayList<>();
        if (commentList != null && !commentList.isEmpty()) {
            for (Comment comment : commentList) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.setComment(comment);
                commentInfo.setMember(userService.getUserById(comment.getUserId()));

                commentInfoList.add(commentInfo);
            }
        }

        model.addAttribute("commentInfoList", commentInfoList);
        return "postDetail";
    }

    @PostMapping("/posts/{postId}/comments")
    @ResponseBody
    public ResponseEntity<Comment> createComment(Authentication authentication,
                                              @RequestBody Comment comment,
                                              @PathVariable Integer postId){

        Member member = userService.getUserByUsername(authentication.getName());
        comment.setUserId(member.getUserId());
        comment.setPostId(postId);
        commentService.createComment(comment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
