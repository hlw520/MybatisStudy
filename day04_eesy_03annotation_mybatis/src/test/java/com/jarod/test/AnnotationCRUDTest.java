package com.jarod.test;

import com.jarod.dao.IUserDao;
import com.jarod.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AnnotationCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in= Resources.getResourceAsStream("SqlMapperConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis annotation");
        user.setAddress("浙江杭州");

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(49);
        user.setUsername("老李");
        user.setAddress("意大利炮");
        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(49);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
//        List<User> users = userDao.findUserByName("%王%");
        List<User> users = userDao.findUserByName("王");
        for(User user:users){
            System.out.println(user);
        }

    }

    @Test
    public void testFindTotal(){
        int total = userDao.findTotalUser();
        System.out.println(total);
    }

}
