package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.persist.content.prototype.ISingleOptionDao;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 单题测试题选项Service实现类
 * @author zmk
 *
 */
@Service
public class SingleOptionServiceImpl extends GenericServiceImpl<SingleOption, Integer> implements ISingleOptionService{

	@Override
	public List<SingleOption> findByTopicId(int topicId) {
		// TODO Auto-generated method stub
		return ((ISingleOptionDao)dao).findByTopicId(topicId);
	}
	

}
