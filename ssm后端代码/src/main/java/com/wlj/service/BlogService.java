package com.wlj.service;

import com.wlj.domain.Blog;

import java.util.List;

public interface BlogService {


    List<Blog> findAll();

    Boolean addBlog(Blog blog);

    List<Blog> findBlogByName(String name);
    Boolean delBlog(int id);
    Blog findBlogById(int id);
}
