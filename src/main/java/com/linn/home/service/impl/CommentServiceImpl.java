package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.home.dao.ArticleDao;
import com.linn.home.dao.CommentDao;
import com.linn.home.entity.Article;
import com.linn.home.entity.Comment;
import com.linn.home.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论service层
 * Created by Administrator on 2018-02-23.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentDao commentDao;

    @Override
    public PageInfo findCommentListByArticleId(PageInfo page, Integer articleId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Comment> commentList = commentDao.findCommentListByArticleId(articleId);
        page = new PageInfo(commentList);
        return page;
    }

    @Override
    public List<Comment> findCommentListByPid(Integer pid) {

        return commentDao.findCommentListByPid(pid);
    }

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int updateCommentById(Comment comment) {
        return commentDao.updateCommentById(comment);
    }
}
