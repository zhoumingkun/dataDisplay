package com.toughguy.educationSystem.dto;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreOption;

public class TopicAndScoreOptionDTO {
	
	private String topic;   //题目
	private List<ScoreOption> scoreOptions;  //选项集
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<ScoreOption> getScoreOptions() {
		return scoreOptions;
	}
	public void setScoreOptions(List<ScoreOption> scoreOptions) {
		this.scoreOptions = scoreOptions;
	}
	
}
