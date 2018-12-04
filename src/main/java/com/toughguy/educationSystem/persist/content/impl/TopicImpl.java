package com.toughguy.educationSystem.persist.content.impl;
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
	

}
