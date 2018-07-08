package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ParamDto;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.shiro.realm.ShiroUser;
import com.linn.frame.util.DateUtils;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.*;
import com.linn.home.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章controller
 * Created by admin on 2018/1/14.
 */
@Controller
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @Resource
    private NoticeService noticeService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private LinkService linkService;

    @Resource
    private UserService userService ;

    /**
     * 前端主页面
     * 默认第一页 每页显示固定条数
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        //每日通知
        Notice notice = noticeService.findActiveNotice();
        model.addAttribute("notice", notice);
        //文章分类
        List<Category> categorys = categoryService.findCategoryAll();
        model.addAttribute("categorys", categorys);
        //归档
        List<Archive> archives = articleService.selectAllArchive();
        model.addAttribute("archives", archives);
        //友情链接
        List<Link> links = linkService.findLinkAll();
        model.addAttribute("links", links);

        return "index";
    }

    /**
     * 到文章列表页面
     *
     * @return
     */
    @RequestMapping(value = "/toArticleList", method = RequestMethod.GET)
    public String toArticleList(HttpServletRequest servletRequest,Model model) {

        String categoryId =  servletRequest.getParameter("categoryId");
        String categoryTitle =  servletRequest.getParameter("categoryTitle");
        String searchContent =  servletRequest.getParameter("searchContent");
        String date =  servletRequest.getParameter("date");

        if (!StringUtils.isEmpty(categoryTitle)) {
            model.addAttribute("title", categoryTitle);
        }
        if (!StringUtils.isEmpty(searchContent)) {
            model.addAttribute("searchContent", searchContent);
        }
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("date", date);
        return "articleList";
    }

    /**
     * 跳转到后台文章列表页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/toArticleList", method = RequestMethod.GET)
    public String toAdminArticleList() {
        return "admin/articleList";
    }

    /**
     * 获取后台管理页面所需的列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/getArticleListDate")
    public ResultTable getArticleListDate(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "limit") Integer limit) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo = articleService.findArticleListAdmin(pageInfo);

        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 弹出编辑文章框
     *
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/toArticleForm", method = RequestMethod.GET)
    public String toArticleForm(@RequestParam(value = "articleId", required = false) Integer articleId, Model model) {
        if (!StringUtils.isEmpty(articleId)) {
            Article article = articleService.selectArticleById(articleId);
            model.addAttribute("article", article);
        }
        List<Category> categorys = categoryService.findCategoryAll();
        model.addAttribute("categorys", categorys);
        return "admin/articleForm";
    }

    /**
     * 文章列表数据
     *
     * @param pageInfo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getArticleListData")
    public PageInfo getArticleListData(PageInfo pageInfo,
                                       @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                       @RequestParam(value = "date", required = false) String date,
                                       @RequestParam(value = "searchContent", required = false) String searchContent) throws ParseException {
        if (pageInfo.getPageNum() <= 0) {
            pageInfo.setPageNum(1);
        }
        pageInfo.setPageSize(SysContent.DEFAULT_PAGE_NUM);
        ParamDto paramDto = new ParamDto();
        paramDto.setCategoryId(categoryId);

        if (!StringUtils.isEmpty(date)) {
            Date firstDay = DateUtils.firstDayByMonth(date);
            Date lastDay = DateUtils.lastDayByMonth(date);
            paramDto.setFirstDay(firstDay);
            paramDto.setLastDay(lastDay);
        }
        paramDto.setSearchContent(searchContent);
        pageInfo = articleService.selectArticleList(paramDto, pageInfo);
        return pageInfo;
    }

    /**
     * 发布文章
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/publishArticle", method = RequestMethod.POST)
    public ResultBean publishArticle(Article article) {
        Subject subject = SecurityUtils.getSubject();
        article.setAuthor(((ShiroUser)subject.getPrincipal()).getUserName()); // to string may be to long for article . weng 2018.7.7
        if (StringUtils.isEmpty(article.getId())) {
            //添加
            article.setGmtCreate(new Date());
            article.setGmtModified(new Date());
            int ret = articleService.addArticle(article);
        } else {
            //更新
            article.setGmtModified(new Date());
            int ret = articleService.updateArticle(article);
        }

        return new ResultBean(SysContent.SUCCESS, "发布成功");
    }

    /**
     * 是否置顶
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/updateArticleStick")
    public ResultBean updateArticleStick(Article article){

        articleService.updateArticleStick(article);
        return new ResultBean(SysContent.SUCCESS, "修改成功");
    }

    /**
     * 是否允许评论
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("admin/updateAllowComment")
    public ResultBean updateAllowComment(Article article){

        articleService.updateAllowComment(article);
        return new ResultBean(SysContent.SUCCESS, "修改成功");
    }

    /**
     * 跳转到文章详情页面
     *
     * @param articleId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toArticleDetail")
    public String toArticleDetail(@RequestParam(value = "articleId") Integer articleId, Model model) {

        if (articleId != null) {
            Article article = articleService.selectArticleById(articleId);
            model.addAttribute("article", article);
            return "articleDetail";
        }
        return "404";
    }

    @ResponseBody
    @RequestMapping("admin/delArticle")
    public ResultBean delArticle(int[] ids) {

        if (ids != null && ids.length > 0) {
            for (int id : ids) {
                int ret = articleService.deleteArticle(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS, "删除成功");
    }

    @RequestMapping("/contactMe")
    public String contactMe() {
        return "contactMe";
    }

    @RequestMapping("/uploadImage")
    public void uploadImage(
            @RequestParam(value = "upload", required = false) CommonsMultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model
    ) {
        List<String> urls = new ArrayList<String>();

        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
            urls.add(request.getContextPath() + "/upload/" + fileName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        String imageContextPath = request.getContextPath() + "/upload/" + fileName;
        String callback = request.getParameter("CKEditorFuncNum");

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        sb.append("</script>");
        writer(response, sb.toString());

    }
}
