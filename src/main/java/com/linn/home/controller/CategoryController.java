package com.linn.home.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Category;
import com.linn.home.service.CategoryService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章分类controller
 * Created by admin on 2018/1/15.
 */
@Controller
public class CategoryController extends BaseController {

    @Resource
    private CategoryService categoryService;


    @RequestMapping("/admin/toCategoryList")
    public String toCategoryList() {
        return "admin/categoryList";
    }

    @ResponseBody
    @RequestMapping("/admin/getCategoryListDate")
    public ResultTable getCategoryListDate(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "limit") Integer limit) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo = categoryService.findCategoryList(pageInfo);
        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 弹出编辑类型框
     *
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/toCategoryForm", method = RequestMethod.GET)
    public String toCategoryForm(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
        if (!StringUtils.isEmpty(categoryId)) {
            Category category = categoryService.findCategoryById(categoryId);
            model.addAttribute("category", category);
        }
        return "admin/categoryForm";
    }

    @ResponseBody
    @RequestMapping("/admin/addOrUpdateCatg")
    public ResultBean addOrUpdateCatg(Category catg) {

        if (StringUtils.isEmpty(catg.getId())) {
            //添加
            catg.setGmtCreate(new Date());
            catg.setGmtModified(new Date());
            int ret = categoryService.addCategory(catg);

        } else {
            //更新
            catg.setGmtModified(new Date());
            int ret = categoryService.updateCategory(catg);
        }

        return new ResultBean(SysContent.SUCCESS, "操作成功");
    }

    @ResponseBody
    @RequestMapping("/admin/delCategory")
    public ResultBean delCategory(int[] ids) {

        if (ids != null && ids.length > 0) {
            for (int id : ids) {
                int ret = categoryService.deleteCategory(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS, "删除成功");
    }
}
