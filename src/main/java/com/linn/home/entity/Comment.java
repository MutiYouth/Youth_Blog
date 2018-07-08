package com.linn.home.entity;

import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 * Created by Administrator on 2018-02-23.
 */
public class Comment {

    private Integer id;
    /**
     * 父ID
     */
    private  Integer pid;
    /**
     * 根ID
     */
    private  Integer rootid;
    /**
     * 会员名
     */
    private String memberName;
    /**
     * 联系信息
     */
    private String contactInfo;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date pdate;
    /**
     * 是否叶子节点
     */
    private Integer isleaf;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 对该评论的回复
     */
    private List<Comment> childCommentList;

    public List<Comment> getChildCommentList() {
        return childCommentList;
    }

    public void setChildCommentList(List<Comment> childCommentList) {
        this.childCommentList = childCommentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", pid=" + pid +
                ", rootid=" + rootid +
                ", memberName='" + memberName + '\'' +
                ", content='" + content + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", pdate=" + pdate +
                ", isleaf=" + isleaf +
                ", articleId=" + articleId +
                ", childCommentList=" + childCommentList +
                '}';
    }
}
