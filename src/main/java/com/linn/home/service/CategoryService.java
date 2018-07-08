package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.frame.entity.ResultBean;
import com.linn.home.entity.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<Category> findCategoryAll();

    PageInfo findCategoryList (PageInfo pageInfo);

    int addCategory (Category catg);

    int updateCategory (Category catg);

    int deleteCategory(int id);

    Category findCategoryById(Integer categoryId);
}