package com.linn.home.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章归档实体类
 * @Author:LNN 2018-01-03 下午 5:04
 */
public class Archive implements Serializable{
	private String date;
	private String count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Archive{" +
				"date='" + date + '\'' +
				", count='" + count + '\'' +
				'}';
	}
}
