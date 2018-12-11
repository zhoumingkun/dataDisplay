package com.toughguy.educationSystem.dto;

import java.util.List;

import com.toughguy.educationSystem.model.content.SingleOption;

public class TopicAndSingleOption {
	
	private String topic;   //题目
	private List<SingleOption> singleOption;  //选项集
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<SingleOption> getSingleOption() {
		return singleOption;
	}
	public void setSingleOption(List<SingleOption> singleOption) {
		this.singleOption = singleOption;
	}
}
