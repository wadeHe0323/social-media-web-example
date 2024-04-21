package com.wade.socialmediawebexample.dto;

import com.wade.socialmediawebexample.model.Comment;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.model.Post;

import java.util.List;

public class PostInfo {
    private Post post;
    private Member member;
    private List<Comment> comments;
    private Boolean myPost;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getMyPost() {
        return myPost;
    }

    public void setMyPost(Boolean myPost) {
        this.myPost = myPost;
    }
}
