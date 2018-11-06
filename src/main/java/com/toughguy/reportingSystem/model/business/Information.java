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
	private String informContent;  //举报内容
	private String picture;   //图片
	private String video;    //视频
	private int state;     //案件状态   -1待审核   1已接案  2侦办中  3已结案   4未结案
	private int informerId;   //举报人id
	private int assessorId;   //评审员(当前用户id)
	private String validFile;    //案件附件（已接案的）
	private String investigationFile;    //案件附件（侦办中的）
	private int feedbackInformationId;     //反馈信息id
	
	private String phoneNumber; //举报人手机号（页面使用）
	private String assessor; //审核员（页面使用）
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
	public String getInformContent() {
		return informContent;
	}
	public void setInformContent(String informContent) {
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
	public int getInformerId() {
		return informerId;
	}
	public void setInformerId(int informerId) {
		this.informerId = informerId;
	}
	public int getAssessorId() {
		return assessorId;
	}
	public void setAssessorId(int assessorId) {
		this.assessorId = assessorId;
	}
	public String getValidFile() {
		return validFile;
	}
	public void setValidFile(String validFile) {
		this.validFile = validFile;
	}
	public String getInvestigationFile() {
		return investigationFile;
	}
	public void setInvestigationFile(String investigationFile) {
		this.investigationFile = investigationFile;
	}
	
	public int getFeedbackInformationId() {
		return feedbackInformationId;
	}
	public void setFeedbackInformationId(int feedbackInformationId) {
		this.feedbackInformationId = feedbackInformationId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAssessor() {
		return assessor;
	}
	public void setAssessor(String assessor) {
		this.assessor = assessor;
	}
	
}
