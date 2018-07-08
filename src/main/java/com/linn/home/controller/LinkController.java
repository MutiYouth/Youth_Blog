package com.linn.home.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Link;
import com.linn.home.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/1/15.
 */
@Controller
public class LinkController extends BaseController{

    @Resource
    private LinkService linkService;

    @RequestMapping("/admin/toLinkList")
    private String toLinkList(){
        return "admin/linkList";
    }

    @ResponseBody
    @RequestMapping("/admin/getLinkListData")
    private ResultTable getLinkListData(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "limit") Integer limit) {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo = linkService.findLinkList(pageInfo);;
        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/admin/toLinkForm", method = RequestMethod.GET)
    public String toLinkForm(@RequestParam(value = "linkId", required = false) Integer linkId, Model model) {
        if (!StringUtils.isEmpty(linkId)) {
            Link link = linkService.findLinkById(linkId);
            model.addAttribute("link", link);
        }
        return "admin/linkForm";
    }

    @ResponseBody
    @RequestMapping("/admin/addOrUpdateLink")
    public ResultBean addOrUpdateCatg(Link link) {
        if(StringUtils.isEmpty(link.getId())) {
            //添加
            link.setGmtCreate(new Date());
            link.setGmtModified(new Date());
            int ret = linkService.addLink(link);
        }else {
            //更新
            link.setGmtModified(new Date());
            int ret = linkService.updateLinkById(link);
        }
        return new ResultBean(SysContent.SUCCESS,"操作成功");
    }

    @ResponseBody
    @RequestMapping("/admin/delLink")
    public ResultBean delLink(int[] ids) {

        if(ids!=null && ids.length > 0){
            for (int id: ids) {
                int ret = linkService.deleteLinkById(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS,"删除成功");
    }
}
