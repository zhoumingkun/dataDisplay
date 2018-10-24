package com.toughguy.reportingSystem.model.business;

import java.awt.TextArea;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 举报信息实体类
 * @author BOBO
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Information extends AbstractModel{
	
	private int id;
	private String threadArea; //线报地域
	private String clueAddress; //线报详址
	private String industryField; //行业领域
	private String informType;  //举报类别
	private TextArea informContent;  //举报内容
	private String picture;   //图片
	private String video;    //视频
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThreadArea() {
		return threadArea;
	}
	public void setThreadArea(String threadArea) {
		this.threadArea = threadArea;
	}
	public String getClueAddress() {
		return clueAddress;
	}
	public void setClueAddress(String clueAddress) {
		this.clueAddress = clueAddress;
	}
	public String getIndustryField() {
		return industryField;
	}
	public void setIndustryField(String industryField) {
		this.industryField = industryField;
	}
	public String getInformType() {
		return informType;
	}
	public void setInformType(String informType) {
		this.informType = informType;
	}
	public TextArea getInformContent() {
		return informContent;
	}
	public void setInformContent(TextArea informContent) {
		this.informContent = informContent;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	
}
