package com.toughguy.educationSystem.dto;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.model.content.ScoreResult;

public class TopicAndScoreOptionDTO {
	
	private int topicId;   //题目id
	private String topic;   //题目
	private List<ScoreOption> scoreOptions;  //选项集
	private List<ScoreRank> scoreRank;  //测试的级别
	private ScoreResult scoreResult;  //测试结果
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
	public List<ScoreRank> getScoreRank() {
		return scoreRank;
	}
	public void setScoreRank(List<ScoreRank> scoreRank) {
		this.scoreRank = scoreRank;
	}
	public ScoreResult getScoreResult() {
		return scoreResult;
	}
	public void setScoreResult(ScoreResult scoreResult) {
		this.scoreResult = scoreResult;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
}
