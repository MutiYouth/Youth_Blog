package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.frame.entity.ParamDto;
import com.linn.home.entity.Archive;
import com.linn.home.entity.Article;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ArticleService {

    PageInfo selectArticleList(ParamDto paramDto,PageInfo page);

    PageInfo selectArticleByCategoryId(int categoryId,PageInfo page);

    PageInfo selectArticleByArchiveDate(Date firstDay,Date lastDay,PageInfo page);

    Article selectArticleById(int articleId);

    List<Archive> selectAllArchive();

    PageInfo findArticleListAdmin(PageInfo pageInfo);

    int addArticle(Article article);

    int deleteArticle(int id);

    int updateArticle(Article article);

    PageInfo selectArticleBySearch(String searchContent,PageInfo page);

    void updateArticleStick(Article article);

    void updateAllowComment(Article article);
}