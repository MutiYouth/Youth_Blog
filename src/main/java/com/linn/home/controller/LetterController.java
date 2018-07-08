package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Letter;
import com.linn.home.entity.Link;
import com.linn.home.service.LetterService;
import com.linn.home.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 站内信controller层
 * Created by admin on 2018/1/15.
 */
@Controller
public class LetterController extends BaseController {

    @Resource
    private LetterService letterService;

    /**
     * 跳转到站内信列表页面
     *
     * @return
     */
    @RequestMapping("/admin/toLetterList")
    public String toLetterList() {
        return "admin/letterList";
    }

    /**
     * 获取站内信列表数据
     *
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/admin/getLetterListData")
    public ResultTable getLetterListData(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "limit") Integer limit) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo = letterService.findLetterList(pageInfo);
        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * letter弹框 查看站内信
     *
     * @param letterId
     * @param model
     * @return
     */
    @RequestMapping("/admin/toLetterForm")
    public String toLetterForm(@RequestParam(value = "letterId") Integer letterId, Model model) {

        Letter letter = letterService.findLetterById(letterId);
        model.addAttribute("letter", letter);
        //更改站内信状态为已读
        letter.setViewStatus(SysContent.HASREAD);
        letterService.updateStatusById(letter);
        return "admin/letterForm";
    }

    @ResponseBody
    @RequestMapping("/addLetter")
    public ResultBean addLetter(Letter letter) {

        letter.setGmtCreate(new Date());
        letter.setGmtModified(new Date());
        int ret = letterService.addLetter(letter);

        return new ResultBean(SysContent.SUCCESS, "操作成功");
    }

    @ResponseBody
    @RequestMapping("/admin/delLetter")
    public ResultBean delLetter(int[] ids) {

        if (ids != null && ids.length > 0) {
            for (int id : ids) {
                int ret = letterService.deleteLetterById(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS, "删除成功");
    }

    /**
     * 查看未读站内信数量
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/admin/findCountByStatus")
    public ResultBean findCountByStatus() {

        int unReadCount = letterService.findCountByStatus(SysContent.UNREAD);
        return new ResultBean(SysContent.SUCCESS, "未读站内信数量", unReadCount);
    }
}
