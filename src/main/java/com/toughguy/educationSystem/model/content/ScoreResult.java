package com.toughguy.educationSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 分值测试结果实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class ScoreResult extends AbstractModel {
	private int id;
	private int  testId;      //对应service_test的id
	private String  result;   //结果答案
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
