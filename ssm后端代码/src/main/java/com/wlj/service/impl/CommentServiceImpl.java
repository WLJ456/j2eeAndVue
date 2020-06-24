package com.wlj.service.impl;

import com.wlj.dao.CommentDao;
import com.wlj.domain.Comment;
import com.wlj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findCommentById(int bid) {
        return commentDao.findCommentById(bid);
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDao.addComment(comment)== 1 ? true : false;
    }
}
