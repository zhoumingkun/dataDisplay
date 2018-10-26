package com.toughguy.reportingSystem.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 举报人实体类
 * @author BOBO
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Informer extends AbstractModel {
	
	private int id;
	private String informerName;    //举报人姓名
	private String idCard;          //身份证号
	private String otherContactWay;  //其他联系方式
	private String workPlace;        //工作单位
	private String livingArea;       //居住地区
	private String address;          //详细地址
	private String phoneNumber;      //手机号码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInformerName() {
		return informerName;
	}
	public void setInformerName(String informerName) {
		this.informerName = informerName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getOtherContactWay() {
		return otherContactWay;
	}
	public void setOtherContactWay(String otherContactWay) {
		this.otherContactWay = otherContactWay;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getLivingArea() {
		return livingArea;
	}
	public void setLivingArea(String livingArea) {
		this.livingArea = livingArea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
