package com.wade.socialmediawebexample.controller;

import com.wade.socialmediawebexample.dto.PostRequest;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.model.Post;
import com.wade.socialmediawebexample.service.PostService;
import com.wade.socialmediawebexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public String getPosts(@RequestParam(defaultValue = "") String username,
                           Model model,
                           Authentication authentication) {

        String myName = authentication.getName();

        if (!username.equals("")) {
            model.addAttribute("postList", postService.getPostsByUserName(myName, username));
        } else {
            model.addAttribute("postList", postService.getPosts(myName));
        }
        return "index";
    }

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<Post> createPost(Authentication authentication,
                                           @RequestBody Post post) {
        Member user = userService.getUserByUsername(authentication.getName());
        post.setUserId(user.getUserId());
        postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/posts/{postId}")
    @ResponseBody
    public ResponseEntity<Post> updatePost(@PathVariable Integer postId,
                           @RequestBody PostRequest postRequest) {

        Post post = postService.getPostById(postId);

        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        postService.updatePost(postId, postRequest);

        Post updatedPost = postService.getPostById(postId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseBody
    public ResponseEntity<Post> deletePost(@PathVariable Integer postId) {
        postService.deletePostById(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
