package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.home.entity.User;

import java.util.List;

/**
 * 用户service层
 * Created by Administrator on 2018-03-06.
 */
public interface UserService {

    User findUserByName(String username);

    int updateUserByUserName(User user);

    int updatePasswordByUsername(User user);

    int findUserByNameAndPass(User user);

    void updateUserAvatar(User user);
}
