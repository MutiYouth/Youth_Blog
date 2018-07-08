package com.linn.frame.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/6/5/005.
 */
public class ParamDto {
    //查询文章列表参数
    //文章类别
    public Integer categoryId;
    //归档日期
    public Date firstDay;
    public Date lastDay;
    //搜索内容
    public String searchContent;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    @Override
    public String toString() {
        return "ParamDto{" +
                "categoryId=" + categoryId +
                ", firstDay=" + firstDay +
                ", lastDay=" + lastDay +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }
}
