package com.wlj.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 */
public class User implements Serializable {


    private Integer id;
 //账户
    private String name;
 //密码
    private String password;
 //个人简介
    private String userdesc;
 //电话号码
    private String phonenumber;
   //邮箱地址
    private String emailaddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userdesc='" + userdesc + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                '}';
    }
}
