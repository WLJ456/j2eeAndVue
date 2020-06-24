package com.wlj.dao;

import com.wlj.domain.Blog;
import com.wlj.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    /**
     * 查询所有博客
     */
    @Select("select * from blogtable ")
    List<Blog> findAll();

    /**
     * 添加博文
     */
    @Insert("insert into blogtable (name,blogTitle,blogText,imagesUrl,isPass,createdDate) values (#{name},#{blogTitle},#{blogText},#{imagesUrl},#{isPass},#{createdDate})")
    int addBlog(Blog blog);

    //通过用户名查询发表的博文
    @Select("select * from blogtable where name=#{name}")
    List<Blog> findBlogByName(String name);

    // //通过id查询博文
    @Select("select * from blogtable where id=#{id} ")
    Blog findBlogById(int id);

    //通过id删除博文
    @Delete("DELETE FROM blogtable WHERE id =#{id}")
    int delBlog(int id);

}
