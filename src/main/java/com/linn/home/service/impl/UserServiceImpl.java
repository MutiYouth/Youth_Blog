package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.frame.util.SysContent;
import com.linn.home.dao.UserDao;
import com.linn.home.entity.User;
import com.linn.home.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户管理service层
 * Created by Administrator on 2018-03-06.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User findUserByName(String username) {

        User user = userDao.findUserByName(username);
        //设置默认头像
        if(StringUtils.isEmpty(user.getAvatar())){
            user.setAvatar(SysContent.DEFAULT_AVATAR);
        }
        return user;
    }

    @Override
    public int updateUserByUserName(User user) {
        user.setGmtModified(new Date());
        return userDao.updateUserByUserName(user);
    }

    @Override
    public int updatePasswordByUsername(User user) {
        return userDao.updatePasswordByUsername(user);

    }

    @Override
    public int findUserByNameAndPass(User user) {
        return userDao.findUserByNameAndPass(user);
    }

    @Override
    public void updateUserAvatar(User user) {
        userDao.updateUserAvatar(user);
    }
}
