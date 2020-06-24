package com.jarod.dao;

import com.jarod.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对，CRUD一共有4个注解
 * @Select  @Insert  @Update  @Delete
 */

@CacheNamespace(blocking = true)
public interface IUserDao {


    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap",value={
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.jarod.dao.IAccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();


    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
//    @ResultMap(value = {"userMap"})
//    @ResultMap({"userMap"})
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 根据用户昵称，模糊查询
     * @param userName
     * @return
     */
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findUserByName(String userName);


}
