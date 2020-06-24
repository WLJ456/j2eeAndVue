package com.wlj.dao;

import com.wlj.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    /**
     * 查询所有用户
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 添加用户

     */
    @Insert("insert into user (name,password,userdesc,phonenumber,emailaddress) values (#{name},#{password},#{userdesc},#{phonenumber},#{emailaddress})")
    int addUser(User user);

    /**
     * 根据用户名查询用户信息

     */

    @Select("select * from user where name=#{username}")
    User findUserByName(String username);

    /**
     * 根据用户id查询

     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 返回int型操作记录条数
     */
    @Update("update user set password=#{password},userdesc=#{userdesc},phonenumber=#{phonenumber},emailaddress=#{emailaddress}where id=#{id}")
    int updateUserMsg(User user);


}
