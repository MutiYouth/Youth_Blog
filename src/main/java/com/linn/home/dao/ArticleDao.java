package com.linn.home.dao;

import com.linn.frame.entity.ParamDto;
import com.linn.home.entity.Archive;
import com.linn.home.entity.Article;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("articleDao")
public interface ArticleDao {

    /**
     * 根据条件查找文章列表
     * @return
     */
    List<Article> selectArticleList(ParamDto paramDto) ;

    /**
     * 根据分类id查找文章
     * @return
     */
    List<Article> selectArticleByCategoryId(Article article) ;

    /**
     * 根据日期范围查找文章
     * @param hashMap
     * @return
     */
    List<Article> selectArticleByArchiveDate(HashMap<String,Object> hashMap) ;

    /**
     * 根据id查询文章信息
     * @param articleId
     * @return
     */
    Article selectArticleById(int articleId) ;

    /**
     * 查找文章归档
     * @return
     */
    List<Archive> selectAllArchive();

    /**
     * 查找文章归档
     * @return
     */
    List<Article> findArticleListAdmin();

    /**
     * 添加文章
     * @param article
     * @return
     * @throws Exception
     */
    int addArticle(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     * @throws Exception
     */
    int deleteArticleById(int id);

    /**
     * 更新文章
     * @param article
     * @return
     * @throws Exception
     */
    int updateArticle(Article article);

    /**
     * 搜索
     * @param hashMap
     * @return
     */
    List<Article> selectArticleBySearch(HashMap<String,Object> hashMap);

    /**
     * 修改文章置顶
     * @param article
     */
    void updateArticleStick(Article article);

    /**
     * 是否允许评论
     * @param article
     */
    void updateAllowComment(Article article);
}
