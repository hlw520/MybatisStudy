package com.jarod.test;

import com.jarod.dao.IUserDao;
import com.jarod.dao.impl.UserDaoImpl;
import com.jarod.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象，创建dao对象
        userDao = new UserDaoImpl(factory);

    }

    @After//用于在测试方法执行之后执行
    public void destory() throws Exception{
        //6.释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user :users){
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUsername("mybatis");
        user.setAddress("上海");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存操作之前：" + user);

        //5.执行保存
        userDao.saveUser(user);

        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(7);
        user.setUsername("肚肚");
        user.setAddress("杭州");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.执行更新
        userDao.updateUser(user);


    }


    /**
     * 测试删除操作
     */
    @Test
    public void testDelete() throws Exception {
        //5.执行删除方法
        userDao.deleteUser(6);
    }

    /**
     * 测试查询一个操作
     */
    @Test
    public void testFindOne() throws Exception {
        //5.执行查询一个方法
        User user = userDao.findById(4);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName() throws Exception {
        //5.执行模糊查询
        List<User> users = userDao.findByName("%李%");
//        List<User> users =  userDao.findByName("李");
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() throws Exception {
        //5.执行查询一个方法
        int count = userDao.findTotal();
        System.out.println(count);
    }


}
