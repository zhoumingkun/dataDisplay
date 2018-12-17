package com.toughguy.educationSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 校园黄页部门实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class XiaoyuanhuangyeDepartment extends AbstractModel {
	private int id;
	private String positionName; //部门名称
	private String phone;        //电话
	private String adress;       //地址
	private int organizationId;  //机构id
	
	private String articleSource;       //来源
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPositionName() {
		return positionName;
	}


	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public int getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}


	public String getArticleSource() {
		return articleSource;
	}


	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}


	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	
}
