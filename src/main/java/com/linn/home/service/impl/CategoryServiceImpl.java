package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.frame.entity.ResultBean;
import com.linn.home.dao.CategoryDao;
import com.linn.home.entity.Category;
import com.linn.home.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类service层
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<Category> findCategoryAll() {
        return categoryDao.selectAllCategory();
    }

    @Override
    public PageInfo findCategoryList(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Category> categories = categoryDao.selectAllCategory();
        pageInfo = new PageInfo(categories);
        return pageInfo;
    }

    @Override
    public int addCategory(Category catg) {
        return categoryDao.addCategory(catg);
    }

    @Override
    public int updateCategory(Category catg) {
        return categoryDao.updateCategoryById(catg);
    }

    @Override
    public int deleteCategory(int id) {
        return categoryDao.deleteCategoryById(id);
    }

    @Override
    public Category findCategoryById(Integer categoryId) {
        return categoryDao.findCategoryById(categoryId);
    }


}