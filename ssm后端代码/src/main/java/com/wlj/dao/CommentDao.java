package com.wlj.dao;

import com.wlj.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    // //通过blogid查询评论
    @Select("select * from Comment where bid=#{bid} ")
    List<Comment> findCommentById(int bid);

    @Insert("insert into Comment (name,bid,context,sendDate) values (#{name},#{bid},#{context},#{sendDate})")
    int addComment(Comment comment);
}
