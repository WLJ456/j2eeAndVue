package com.wlj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //    微博对应id
    private int bid;
    //评论id，唯一标识
    private int id;
    //评论者的用户名
    private  String name;
    //评论内容
    private  String context;
    //评论日期

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sendDate;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "bid=" + bid +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
