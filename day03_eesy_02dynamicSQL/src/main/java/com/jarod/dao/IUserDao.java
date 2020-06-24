package com.jarod.dao;

import com.jarod.domain.QueryVo;
import com.jarod.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();


    /**
     * 根据id查询用户信息
     * @param userId
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);


    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件查询
     * @param user 查询的条件：可能有用户名，可能有性别，也可能有地址，也可能都有，也可能都没有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryVo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
