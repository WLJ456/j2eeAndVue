package com.wlj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//博客类
public class Blog implements Serializable {
    // 获取当前时间
//    Date time = new Date(System.currentTimeMillis());
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String current = sdf.format(time);
//博文标题
    private  int id;
    private String blogTitle;
    //博文内容
    private String blogText;
    //上传图片地址
    private String imagesUrl;
    //作者
    private String name;
    //发表博文时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //解决传到前端为时间戳的问题
    private Date createdDate;
    //    是否被审核
    private int isPass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogText='" + blogText + '\'' +
                ", imagesUrl='" + imagesUrl + '\'' +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", isPass=" + isPass +
                '}';
    }
}
