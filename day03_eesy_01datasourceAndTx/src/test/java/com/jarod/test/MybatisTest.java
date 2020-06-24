package com.jarod.test;

import com.jarod.dao.IUserDao;
import com.jarod.domain.QueryVo;
import com.jarod.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.后去SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destory() throws Exception{
        //提交事务
//        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
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
        user.setUserName("mybatis");
        user.setUserAddress("上海");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

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
        user.setUserId(6);
        user.setUserName("肚肚");
        user.setUserAddress("杭州");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
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


    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() throws Exception {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%李%");
        vo.setUser(user);
        //5.执行模糊查询
        List<User> users = userDao.findUserByVo(vo);
//        List<User> users =  userDao.findByName("李");
        for(User u : users){
            System.out.println(u);
        }
    }
}
