package com.wade.socialmediawebexample.dto;

import com.wade.socialmediawebexample.model.Comment;
import com.wade.socialmediawebexample.model.Member;

public class CommentInfo {
    private Comment comment;
    private Member member;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
