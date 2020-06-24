package com.wlj.controller;

import com.wlj.domain.User;
import com.wlj.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
//一级目录
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //上传图片回调删除
    @RequestMapping("/delImg")
    @ResponseBody
    public Boolean delImg(@RequestParam(name = "imgUrl") String imgUrl, HttpServletRequest req) {
        String path = req.getSession().getServletContext().getRealPath("");
        imgUrl = imgUrl.substring(1);
        String url = path + imgUrl;
        String urls = url.replaceAll("/", "\\\\");
        File filePath = new File(urls);
        if (filePath.exists() && filePath.isFile()){
            if (filePath.delete()) {
                System.out.println("删除成功！");
                return true;
            } else {
                System.out.println("删除失败！");
                return false;
            }
        }else {
            return  false;
        }
    }

    ;

    //照片上传
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("upload") MultipartFile upload, HttpServletRequest req)
            throws IllegalStateException, IOException {

        //获取文件在服务器的储存位置
        String path = req.getSession().getServletContext().getRealPath("/upload");
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = upload.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            upload.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返不返回文件名，前端自行拼接
            String url = "/upload/" + date;
            System.out.println(url);
            return url;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
        }

        return "false";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(@RequestParam(name = "username") String username) {
        System.out.println(username);
        if (username.equalsIgnoreCase("admin")) {
            List<User> users = userService.findAll();
            System.out.println(users);
            return users;
        } else {
            return null;
        }
    }

    //登录方法
    @RequestMapping(path = "/login")
    @ResponseBody
    public User login(@RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password) throws Exception {
        System.out.println(username + password);
        User user = userService.findUserByName(username);
        System.out.println(user);
        if (user != null && user.getPassword().equals(password)) {
            //将用户名放入Model
            return user;
        }
        return null;
    }

    //修改个人信息方法
    @RequestMapping(path = "/updata")
    @ResponseBody
    public Boolean updata(User user) {
        System.out.println("请求成功");
        System.out.println(user);
        boolean flag = userService.updateUserMsg(user);
        return flag;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Boolean addUser(User user) throws Exception {
        System.out.println(user);
        boolean isexit = userService.isExitName(user.getName());
        if (isexit) {
            //用户已存在
            return false;
        } else {
            return userService.addUser(user);
        }
    }

}
