package com.jarod.dao.impl;

import com.jarod.dao.IUserDao;
import com.jarod.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.jarod.dao.IUserDao.findAll");//参数就是能获取配置信息的key
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.insert("com.jarod.dao.IUserDao.saveUser",user);
        session.commit();
        session.close();

    }

    public void updateUser(User user) {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        session.update("com.jarod.dao.IUserDao.updateUser",user);
        session.commit();
        session.close();
    }

    public void deleteUser(Integer userId) {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        session.delete("com.jarod.dao.IUserDao.deleteUser",userId);
        session.commit();
        session.close();
    }

    public User findById(Integer userId) {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        User user = session.selectOne("com.jarod.dao.IUserDao.findById",userId);
        return user;
    }

    public List<User> findByName(String username) {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.jarod.dao.IUserDao.findByName",username);
        return users;
    }

    public int findTotal() {
        //1.根据facory获取SqlSession对象
        SqlSession session = factory.openSession();
        int i = session.selectOne("com.jarod.dao.IUserDao.findTotal");
        return i;
    }
}
