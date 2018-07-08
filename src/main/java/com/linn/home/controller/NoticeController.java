package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Notice;
import com.linn.home.entity.User;
import com.linn.home.service.NoticeService;
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
 * Created by admin on 2018/1/15.
 */
@Controller
public class NoticeController extends BaseController {

    @Resource
    private NoticeService noticeService;

    /**
     * 跳转到通知列表也main
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/toNoticeList")
    public String toNoticeList() {

        return "admin/noticeList";
    }

    /**
     * 获取通知列表分页数据
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/admin/getNoticeListData")
    public ResultTable getNoticeListData(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "limit") Integer limit) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo = noticeService.findNoticeList(pageInfo);
        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 跳转到通知form
     * @param noticeId
     * @param model
     * @return
     */
    @RequestMapping("/admin/toNoticeForm")
    public String toNoticeForm(@RequestParam(value = "noticeId", required = false) Integer noticeId, Model model){
        if (!StringUtils.isEmpty(noticeId)) {
            Notice notice = noticeService.findNoticeById(noticeId);
            model.addAttribute("notice", notice);
        }
        return "admin/noticeForm";
    }

    @ResponseBody
    @RequestMapping("/admin/addOrUpdateNotice")
    public ResultBean addOrUpdateNotice(Notice notice) {
        notice = jsFilter(notice);
        if(StringUtils.isEmpty(notice.getId())) {
            //添加
            notice.setGmtCreate(new Date());
            notice.setGmtModified(new Date());
            int ret = noticeService.addNotice(notice);
        }else {
            //更新
            notice.setGmtModified(new Date());
            int ret = noticeService.updateNotice(notice);
        }

        return new ResultBean(SysContent.SUCCESS,"操作成功");
    }

    /**
     * 删除通知
     * @param ids
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/admin/delNotice")
    public ResultBean delNotice(int[] ids) {

        if(ids!=null && ids.length > 0){
            for (int id: ids) {
                int ret = noticeService.deleteNoticeById(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS,"删除成功");
    }


    @ResponseBody
    @RequestMapping("admin/updateNotcieIsActive")
    public ResultBean updateNotcieIsActive(Notice notice){

        noticeService.updateNotcieIsActive(notice);
        return new ResultBean(SysContent.SUCCESS,"修改成功");
    }

    private Notice jsFilter(Notice notice){
        if(!StringUtils.isEmpty(notice.getTitle())) {
            String title = notice.getTitle().replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");;
            notice.setTitle(title);

        }
        if(!StringUtils.isEmpty(notice.getContent())) {
            String content = notice.getContent().replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");;
            notice.setContent(content);
        }
        if(!StringUtils.isEmpty(notice.getAuthor())) {
            String author = notice.getAuthor().replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");;
            notice.setAuthor(author);
        }
        return notice;
    }
}
