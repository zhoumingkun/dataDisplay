package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 心理测试题题目Service层接口类
 * @author zmk
 *
 */
public interface ITopicService extends IGenericService<Topic, Integer>{
	/**
	 * 根据标题查询
	 * */
	public List<Topic> findByTopic(String topic);
}
