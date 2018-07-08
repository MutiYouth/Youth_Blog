package com.linn.frame.shiro.realm;

import com.linn.home.dao.UserDao;
import com.linn.home.entity.User;
import com.linn.home.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义shiro的realm
 * Created by Administrator on 2018/5/29/029.
 */
public class CustomRealm extends AuthorizingRealm {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserDao userDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser  = (ShiroUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUsername(shiroUser);
        Set<String> permissions = getPermisionByUsername(shiroUser);

        SimpleAuthorizationInfo authorization  = new SimpleAuthorizationInfo();
        authorization.setRoles(roles);
        authorization.setStringPermissions(permissions);
        return authorization;
    }

    //根据用户id查找权限
    private Set<String> getPermisionByUsername(ShiroUser shiroUser) {

        List<String> list = userDao.getPermisisonByUserId(shiroUser.getId());
        Set<String> permissions = new HashSet<String>(list);
        return permissions;
    }

    //根据用户id查找用户角色
    private Set<String> getRolesByUsername(ShiroUser shiroUser) {
        List<String> list = userDao.getRolesByUserId(shiroUser.getId());
        Set<String> roles = new HashSet<String>(list);
        return roles;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1,从主体传过来的认证信息中，获得用户名
        String username = (String) authenticationToken.getPrincipal();
        //2,通过用户名从数据库获取凭证
        User user = null;
        try {
            user = userDao.findUserByName(username);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new AuthenticationException(e);
        }

        if(user == null){
            return  null;
        }

        //要放在作用域中的东西，请在这里进行操作
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(user),user.getPassWord(),getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));//将用户名作为盐加密密码
        return authenticationInfo;
    }

    //密码加盐
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "admin";//密码原值
        Object salt = "guest";//盐值
        int hashIterations = 1;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println(result);
    }
}
