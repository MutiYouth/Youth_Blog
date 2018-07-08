package com.linn.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * @Author:LNN 2018-01-02 下午 5:53
 */
public class Article implements Serializable{

	private Integer id;
	private Integer categoryId;
	private String title;
	private String author;
	private User user;
	private String label;
	private String content;
	private Date gmtCreate;
	private Date gmtModified;
	private Integer sortNo;
	private Integer allowComment;
	private Integer isStick;
	private Integer isDraft;
	private Integer isDeleted;
	//留言数量
	private Integer commentCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getIsStick() {
		return isStick;
	}

	public void setIsStick(Integer isStick) {
		this.isStick = isStick;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Integer allowComment) {
		this.allowComment = allowComment;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", label='" + label + '\'' +
				", content='" + content + '\'' +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				", sortNo=" + sortNo +
				", allowComment=" + allowComment +
				", isStick=" + isStick +
				", isDraft=" + isDraft +
				", isDeleted=" + isDeleted +
				", commentCount=" + commentCount +
				", user=" + user +
				'}';
	}
}
