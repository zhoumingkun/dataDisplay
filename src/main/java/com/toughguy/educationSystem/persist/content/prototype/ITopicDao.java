package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 心理测试题目Dao接口类
 * @author zmk
 *
 */
public interface ITopicDao extends IGenericDao<Topic, Integer>{
	/**
	 * 根据标题查询
	 * */
	public List<Topic> findByTopic(String topic);
	/**
	 * 根据题目id查询
	 * */
	public List<Topic> findByTestId(int testId);

}
