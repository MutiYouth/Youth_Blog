package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Article;
import com.linn.home.entity.Comment;
import com.linn.home.entity.Notice;
import com.linn.home.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 文章评论controller
 * Created by Administrator on 2018-02-23.
 */
@Controller
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;

    /**
     * 查找文章下所有评论
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/toCommentList")
    public PageInfo toCommentList(PageInfo pageInfo, String articleId) {

        if (pageInfo.getPageNum() <= 0) {
            pageInfo.setPageNum(1);
        }
        pageInfo.setPageSize(SysContent.DEFAULT_PAGE_NUM);
        pageInfo = commentService.findCommentListByArticleId(pageInfo, Integer.parseInt(articleId));
        List<Comment> commentList = pageInfo.getList();
        for (Comment comment : commentList) {
            if (comment.getIsleaf().equals(SysContent.YES)) {
                //不是叶子节点
                findChildComment(comment);
            }
        }
        return pageInfo;
    }

    /**
     * 查找评论下面的回复
     *
     * @param comment
     * @throws Exception
     */
    private void findChildComment(Comment comment) {

        List<Comment> childCommentList;
        childCommentList = commentService.findCommentListByPid(comment.getId());
        if (childCommentList != null && childCommentList.size() > 0) {

            comment.setChildCommentList(childCommentList);

            for (Comment childComment : childCommentList) {
                if (childComment.getIsleaf().equals(SysContent.YES)) {
                    //不是叶子节点
                    findChildComment(childComment);
                }
            }
        }
    }


    /**
     * 添加评论
     *
     * @param comment
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addComment")
    public ResultBean addComment(Comment comment) {
        comment = jsFilter(comment);
        comment.setIsleaf(SysContent.NO);
        int ret = commentService.addComment(comment);
        if (ret > 0) {
            Comment pComm = new Comment();
            pComm.setId(comment.getPid());
            pComm.setIsleaf(SysContent.YES);//非叶子节点
            commentService.updateCommentById(pComm);
        }
        return new ResultBean(SysContent.SUCCESS, "操作成功");
    }

    private Comment jsFilter(Comment comment){
        if(!StringUtils.isEmpty(comment.getContent())) {
            String content = comment.getContent()
                    .replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");
            comment.setContent(content);
        }
        if(!StringUtils.isEmpty(comment.getContactInfo())){
            String contactInfo = comment.getContactInfo()
                    .replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");
            comment.setContactInfo(contactInfo);
        }
        if(!StringUtils.isEmpty(comment.getMemberName()))
        {
            String memberName = comment.getMemberName()
                    .replaceAll("<script>", "(script)")
                    .replaceAll("</script>","(/script)");
            comment.setMemberName(memberName);
        }

        return comment;
    }
}
