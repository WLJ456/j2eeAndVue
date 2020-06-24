package com.wlj.test;

import com.wlj.dao.UserDao;
import com.wlj.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBits {

    @Test
    public  void run1() throws  Exception {
        //加载配置文件
      InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
      //创建sqlsession
       SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession=factory.openSession();
        //获取代理对象
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        //查询所有数据
        List<User> list=userDao.findAll();
        for (User user:list){
            System.out.println(user);
        }
        //关闭资源
        sqlSession.close();
        in.close();
    }
    @Test
    public  void run2() throws  Exception {
        User user=new User();
        user.setName("wljzdd");
        user.setPassword("123456");
        user.setUserdesc("文罗江帅气");
        user.setEmailaddress("123456789@qq.com");
        user.setPhonenumber("18384927234");
        //加载配置文件
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建sqlsession
        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession=factory.openSession();
        //获取代理对象
        UserDao userDao=sqlSession.getMapper(UserDao.class);
        //查询所有数据
       int list=userDao.addUser(user);
       System.out.println(list);
       sqlSession.commit();

        //关闭资源
        sqlSession.close();
        in.close();
    }
}
