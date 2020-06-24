package com.wlj.controller;

import com.wlj.domain.Blog;
import com.wlj.domain.User;
import com.wlj.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wlj.controller.UserController;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    //查询所有，可供管理员使用
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Blog> findAll(Model model) {
        List<Blog> blogs = blogService.findAll();

        return blogs;
    }

    //    发表微博接口
    @RequestMapping("/addBlog")
    @ResponseBody
    public Boolean addBlog(Blog blog) throws Exception {
        System.out.println(blog);
        java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        blog.setCreatedDate(timeStamp);
        return blogService.addBlog(blog);
    }

    //查询微博通过用户名
    @RequestMapping("/findBlogByName")
    @ResponseBody
    public List<Blog> findBlogByName(@RequestParam(name = "name") String name) {
        System.out.println(name);
        List<Blog> blogs = blogService.findBlogByName(name);
        return blogs;
    }

    //作者删除微博,同时删除服务器上的图片
    @RequestMapping("/delBlog")
    @ResponseBody
    public Boolean delBlog(@RequestParam(name = "id") String id, HttpServletRequest request) {
        int ids = Integer.parseInt(id);
        System.out.println(id);
        //同时删除保存在服务器的图片
        Blog blog = blogService.findBlogById(ids);
        String imgurl = blog.getImagesUrl();
        System.out.println(imgurl);
        //获取根路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + "/";
        System.out.println(basePath);
        String url = imgurl.substring(basePath.length());
        UserController userController = new UserController();
        Boolean flag = userController.delImg(url, request);
        if (flag==true){
            return blogService.delBlog(ids);
        }else {
            return false;
        }

    }

    //通过id查找微博
    @RequestMapping("/findBlogById")
    @ResponseBody
    public Blog findBlogById(@RequestParam(name = "id") String id) {
        System.out.println("这是通过id查找");
        int ids = Integer.parseInt(id);
        return blogService.findBlogById(ids);
    }

}
