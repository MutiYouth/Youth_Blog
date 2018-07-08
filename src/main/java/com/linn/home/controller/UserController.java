package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.shiro.realm.ShiroUser;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Link;
import com.linn.home.entity.User;
import com.linn.home.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by Administrator on 2018-03-08.
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 跳转到用户资料修改页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "admin/toUserSet", method = RequestMethod.GET)
    public String toUserSet(Model model) {
        ShiroUser shiroUser = ShiroUser.getShiroUser();
        if (shiroUser != null) {
            String username = shiroUser.getUserName();
            User user = userService.findUserByName(username);

            model.addAttribute("user", user);
            return "admin/userSet";
        }else{
            return "/error";
        }

    }

    /**
     * 修改用户个人信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/updateUserInfo")
    private ResultBean updateUserInfo(User user) {
        if (StringUtils.isEmpty(user.getNickname())) {
            return new ResultBean(SysContent.ERROR, "昵称不能为空");
        }
        if (!StringUtils.isEmpty(user.getSignMsg()) && user.getSignMsg().length() > 30) {
            return new ResultBean(SysContent.ERROR, "签名过长，最多30个字符");
        }
        user.setUserName(ShiroUser.getShiroUser().getUserName());
        userService.updateUserByUserName(user);
        return new ResultBean(SysContent.SUCCESS, "操作成功");
    }

    /**
     * 修改密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/updateUserPass")
    public ResultBean updateUserPass(@RequestParam(value = "nowpass") String nowpass
            , @RequestParam(value = "pass") String pass
            ,@RequestParam(value = "repass") String repass) {
//        Subject subject = SecurityUtils.getSubject();
//        boolean admin = subject.hasRole("admin");
//        boolean guest = subject.hasRole("guest");
//        boolean sel = subject.isPermitted("select");

        ShiroUser shiroUser = ShiroUser.getShiroUser();
        if(shiroUser == null){
            return new ResultBean(SysContent.ERROR, "未登录");
        }
        User user = new User();
        user.setUserName(shiroUser.getUserName());
        Md5Hash hashNowPass = new Md5Hash(nowpass, user.getUserName());
        user.setPassWord(hashNowPass.toString());
        int count = userService.findUserByNameAndPass(user);
        if(count <= 0){
            return new ResultBean(SysContent.ERROR, "原密码不正确");
        }
        if(!pass.equals(repass)){
            return new ResultBean(SysContent.ERROR, "两次密码不一致");
        }
        if(pass.length() < 5){
            return new ResultBean(SysContent.ERROR, "密码长度为5到16个字符");
        }
        Md5Hash hashPass = new Md5Hash(pass, user.getUserName());
        user.setPassWord(hashPass.toString());

        int ret = userService.updatePasswordByUsername(user);

        return new ResultBean(SysContent.SUCCESS, "操作成功");
    }

    /**
     * 上传头像图片
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/uploadAvatar")
    public ResultBean uploadAvatar(@RequestParam(value = "file") CommonsMultipartFile file,
                                   HttpServletRequest request){

        String path = request.getSession().getServletContext().getRealPath("upload/avatar");
        String fileName = file.getOriginalFilename();

        User user = new User();
        ShiroUser shiroUser = ShiroUser.getShiroUser();
        user.setUserName(shiroUser.getUserName());

        // 修改保存的蹄片名称格式 username_avatar
        if(fileName.indexOf(".")>=0){
            String suffix =  fileName.substring(fileName.indexOf("."));
            fileName = shiroUser.getUserName() + "_avatar" + suffix;
        }

            File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String fileLocation  = "";
        //保存
        try {
            file.transferTo(targetFile);
            fileLocation = request.getContextPath() + "/upload/avatar/" + fileName;
            user.setAvatar(fileLocation);
            userService.updateUserAvatar(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return  new ResultBean(SysContent.ERROR, "保存失败");
        }
        return  new ResultBean(SysContent.SUCCESS, "保存成功",fileLocation);
    }

}
