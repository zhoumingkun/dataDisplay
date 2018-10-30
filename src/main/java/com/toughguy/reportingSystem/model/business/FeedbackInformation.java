package com.toughguy.reportingSystem.model.business;

import java.awt.TextArea;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 反馈信息实体类
 * @author BOBO
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class FeedbackInformation extends AbstractModel{
	
	private int id;
	private String feedbackInformation;    //反馈信息
	private int state;           //状态，-1未通过反馈信息     1已通过反馈信息
	private int type;            //类型（用户自己填写，为了筛选）
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeedbackInformation() {
		return feedbackInformation;
	}
	public void setFeedbackInformation(String feedbackInformation) {
		this.feedbackInformation = feedbackInformation;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
   
	
}
