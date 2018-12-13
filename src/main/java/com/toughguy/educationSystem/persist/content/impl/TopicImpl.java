package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.persist.content.prototype.ITopicDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 心理测试题目Dao实现类
 * @author zmk
 *
 */
@Repository
public class TopicImpl extends GenericDaoImpl<Topic, Integer> implements ITopicDao{

	@Override
	public Topic findByTopic(String topic) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + "findByTopic", topic);
	}

	@Override
	public List<Topic> findByTestId(int testId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTestId",testId);
	}
	

}
