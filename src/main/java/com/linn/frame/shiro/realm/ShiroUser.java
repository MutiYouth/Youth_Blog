package com.linn.frame.shiro.realm;

import com.linn.home.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;
import java.util.Date;

/**
 *  Shiro框架的用户实体类
 * Created by Administrator on 2018/7/4.
 */
public class ShiroUser  implements Serializable {

    private Integer id;
    private String nickname;
    private String userName;
    private String passWord;
    private Integer sex;
    private String city;
    private String signMsg;
    private String email;
    private String avatar;
    private Date gmtCreate;
    private Date gmtModified;

    public  ShiroUser(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.userName = user.getUserName();
        this.passWord = user.getPassWord();
        this.sex = user.getSex();
        this.city = user.getCity();
        this.signMsg = user.getSignMsg();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.gmtCreate = user.getGmtCreate();
        this.gmtModified = user.getGmtModified();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取当前登陆用户信息
     *
     * @return
     */
    public static ShiroUser getShiroUser()
    {
        Subject user = SecurityUtils.getSubject();
        if (user == null)
        {
            return null;
        }
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        if (shiroUser == null)
        {
            return null;
        }
        return shiroUser;
    }

    @Override
    public String toString() {
        return "ShiroUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", signMsg='" + signMsg + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
