package com.oy.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String content;
	private Admin admin;
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", admin=" + admin + ", date=" + date
				+ "]";
	}

}
