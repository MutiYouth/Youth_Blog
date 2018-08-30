package com.linn.home.controller;

import com.linn.frame.controller.BaseController;
import com.linn.frame.shiro.realm.ShiroUser;
import com.linn.home.entity.User;
import com.linn.home.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 登录controller
 * Created by Administrator on 2018-02-07.
 */
@Controller
public class LoginController extends BaseController{

    @Resource
    private UserService userService ;

    /**
     * 登出
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/logout")
    private String toAdminLogout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            subject.logout();
        }

        return "redirect:/admin";
    }

    /**
     * 跳转到登录页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    private String toAdminLogin() {

        return "admin/login";
    }

    // 用户登录
    @RequestMapping(value = "/subLogin",method = RequestMethod.POST)
    public String subLogin(@RequestParam(value = "username") String username
            ,@RequestParam(value = "password") String password
            ,@RequestParam(value = "rememberMe",required=false) String rememberMe
            ,Model model){

        User user = userService.findUserByName("weng");



        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        if(!StringUtils.isEmpty(rememberMe) && rememberMe.equals("on")){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
        }catch (UnknownAccountException uae)
        {
            logger.error(uae.getMessage(), uae);
            model.addAttribute("errMsg","账号不存在!");
            return "admin/login";
        }
        catch (IncorrectCredentialsException ice)
        {
            logger.error(ice.getMessage(), ice);
            model.addAttribute("errMsg","密码错误!");
            return "admin/login";
        }
        catch (LockedAccountException lae)
        {
            logger.error(lae.getMessage(), lae);
            model.addAttribute("errMsg","账号被锁定!");
            return "admin/login";
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            model.addAttribute("errMsg","未知错误,请联系管理员!");
            return "admin/login";
        }

        return "redirect:/admin";
    }


    /**
     * 跳转到后台管理主页
     * @return
     */
    @RequestMapping("/admin")
    public String toAdminIndex(Model model){
        ShiroUser shiroUser = ShiroUser.getShiroUser();
        if(shiroUser == null){
            return  "admin/login";
        }
        model.addAttribute("user",shiroUser);
        return "admin/index";
    }


    /**
     * 没有权限
     *
     * @param model 数据传输对象
     * @return 返回视图
     */
    @RequestMapping(value = "/unauth")
    public String unauth(Model model)
    {
        if (!SecurityUtils.getSubject().isAuthenticated())
        {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 系统错误页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String toError(Model model)
    {
        return "common/error";
    }


}