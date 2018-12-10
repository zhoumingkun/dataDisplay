package com.toughguy.educationSystem.dto;

public class StatisticalDTO {
	
	private int testSum;   //平台总题数
	private int testerSum;  //测试总人次
	private int testerPassSum;  //测试合格人次
	private int testerFailureSum;   //测试不合格人次
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
	
	
}
