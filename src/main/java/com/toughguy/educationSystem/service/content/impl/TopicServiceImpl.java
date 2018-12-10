package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.persist.content.prototype.ITopicDao;
import com.toughguy.educationSystem.service.content.prototype.ITopicService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 心理测试题题目Service实现类
 * @author zmk
 *
 */
@Service
public class TopicServiceImpl extends GenericServiceImpl<Topic, Integer> implements ITopicService{

	@Override
	public List<Topic> findByTopic(String topic) {
		// TODO Auto-generated method stub
		return ((ITopicDao)dao).findByTopic(topic);
	}
	

}
