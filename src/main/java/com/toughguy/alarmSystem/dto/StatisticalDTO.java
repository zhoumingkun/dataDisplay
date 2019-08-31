package com.toughguy.alarmSystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatisticalDTO {
	
	private int testSum;   //平台总题数
	private int testerSum;  //测试总人次
	private int testerPassSum;  //测试合格人次
	private int testerFailureSum;   //测试不合格人次
	
	private int oneTesterSum;  //某题的测试总人 次
	private int oneTesterPassSum; //某题测试合格人次
	private int oneTesterFailureSum; //莫题测试不合格人次
	
	private Date createTime;//创建时间(accountResult的创建时间)
	public int getTestSum() {
		return testSum;
	}
	public void setTestSum(int testSum) {
		this.testSum = testSum;
	}
	public int getTesterSum() {
		return testerSum;
	}
	public void setTesterSum(int testerSum) {
		this.testerSum = testerSum;
	}
	public int getTesterPassSum() {
		return testerPassSum;
	}
	public void setTesterPassSum(int testerPassSum) {
		this.testerPassSum = testerPassSum;
	}
	public int getTesterFailureSum() {
		return testerFailureSum;
	}
	public void setTesterFailureSum(int testerFailureSum) {
		this.testerFailureSum = testerFailureSum;
	}
	public int getOneTesterSum() {
		return oneTesterSum;
	}
	public void setOneTesterSum(int oneTesterSum) {
		this.oneTesterSum = oneTesterSum;
	}
	public int getOneTesterPassSum() {
		return oneTesterPassSum;
	}
	public void setOneTesterPassSum(int oneTesterPassSum) {
		this.oneTesterPassSum = oneTesterPassSum;
	}
	public int getOneTesterFailureSum() {
		return oneTesterFailureSum;
	}
	public void setOneTesterFailureSum(int oneTesterFailureSum) {
		this.oneTesterFailureSum = oneTesterFailureSum;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
