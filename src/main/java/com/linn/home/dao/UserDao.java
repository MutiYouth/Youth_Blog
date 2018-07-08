package com.linn.home.dao;

import com.linn.home.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao层
 * Created by Administrator on 2018-03-06.
 */
@Repository("userDao")
public interface UserDao {

    /**
     * 更新用户密码
     * @param user
     * @return
     * @throws Exception
     */
    int updateUserByUserName(User user);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    int updatePasswordByUsername(User user);

    /**
     * 验证密码是否正确
     * @param user
     * @return
     */
    int findUserByNameAndPass(User user);

    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 根据用户名查找用户的角色
     * @param userId
     * @return 角色集合
     */
    List<String> getRolesByUserId(Integer userId);

    /**
     * 根据用户名查找用户密码
     * @param username
     * @return 用户
     */
    User getPasswordByUsername(String username);

    /**
     * 通过用户名查找用户权限集合
     * @param userId
     * @return
     */
    List<String> getPermisisonByUserId(Integer userId);

    /**
     * 更新头像地址
     * @param user
     */
    void updateUserAvatar(User user);
}
