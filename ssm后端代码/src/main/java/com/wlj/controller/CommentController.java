package com.wlj.controller;


import com.wlj.domain.Blog;
import com.wlj.domain.Comment;
import com.wlj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //通过id查找评论
    @RequestMapping("/findCommentById")
    @ResponseBody
    public List<Comment> findCommentById(@RequestParam(name = "id") String id){
        int bid=Integer.parseInt(id);
        return  commentService.findCommentById(bid);
    }

    //    发表评论接口
    @RequestMapping("/addComment")
    @ResponseBody
    public Comment addComment(Comment comment) throws Exception {
        System.out.println(comment);
        java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        comment.setSendDate(timeStamp);
        if(commentService.addComment(comment)==true){
            //以供前端实时更新数据
            return comment;
        }else {
            return null;
        }

    }
}
