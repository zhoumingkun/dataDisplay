package com.toughguy.reportingSystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.toughguy.reportingSystem.util.JsonUtil;
/**
 * 举报信息页面显示
 * @author BOBO
 *
 */
public class InformationDTO {
	
	private int sum;  //扫黑除恶案件总数
	
	private int invalidNumber;   //无效案件数量
	
	private int validNumber;    //已接警扫黑除恶案件数量
	
 	private int endNumber;      //已结案数量
 	
 	
	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getInvalidNumber() {
		return invalidNumber;
	}


	public void setInvalidNumber(int invalidNumber) {
		this.invalidNumber = invalidNumber;
	}


	public int getValidNumber() {
		return validNumber;
	}


	public void setValidNumber(int validNumber) {
		this.validNumber = validNumber;
	}


	public int getEndNumber() {
		return endNumber;
	}


	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}


	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}
