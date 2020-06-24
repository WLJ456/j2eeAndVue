package com.wlj.service;

import com.wlj.domain.Comment;

import java.util.List;

public interface CommentService {



    List<Comment> findCommentById(int bid);

    boolean addComment(Comment comment);
}
