package com.wlj.service.impl;

import com.wlj.dao.BlogDao;
import com.wlj.domain.Blog;
import com.wlj.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BlogService")
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    //查找所有博文
    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public Boolean addBlog(Blog blog) {
        return blogDao.addBlog(blog)== 1 ? true : false;
    }

    @Override
    public List<Blog> findBlogByName(String name) {
        return blogDao.findBlogByName(name);
    }

    @Override
    public Boolean delBlog(int id) {
        return blogDao.delBlog(id)== 1 ? true : false;
    }

    @Override
    public Blog findBlogById(int id) {
        return blogDao.findBlogById(id);
    }
}
