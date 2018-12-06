package com.toughguy.educationSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 分值测试题选项 实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class ScoreOption extends AbstractModel {
	private int id;
	private int  topicId;     //测试题目id  service_test_topic
	private String option;    //选项（A,B,C,D）
	private int grade;        //分数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	
	

	
}
