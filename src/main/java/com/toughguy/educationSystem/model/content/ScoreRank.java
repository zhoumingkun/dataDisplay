package com.toughguy.educationSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 分值级别表 实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class ScoreRank extends AbstractModel {
	private int id;
	private int  testId;       //对应service_test的id
	private int  rank;         //级别（优秀  良好  危险）
	private String  range;     //测评范围
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	
	

	
}
