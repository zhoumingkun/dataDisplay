package com.toughguy.educationSystem.dto;

import java.util.List;

import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;

public class XiaoyuanhuangyeDTO {
	
	private String organizationName; //机构名称
	private int type;             //类型（1管理机构，2学院机构）
	private String author;       //作者
	private String articleSource;       //来源
	private List<XiaoyuanhuangyeDepartment> xiaoyuanhuangyeDepartment;  //校园黄页部门集
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getArticleSource() {
		return articleSource;
	}
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
	public List<XiaoyuanhuangyeDepartment> getXiaoyuanhuangyeDepartment() {
		return xiaoyuanhuangyeDepartment;
	}
	public void setXiaoyuanhuangyeDepartment(List<XiaoyuanhuangyeDepartment> xiaoyuanhuangyeDepartment) {
		this.xiaoyuanhuangyeDepartment = xiaoyuanhuangyeDepartment;
	}
	
	
} 
