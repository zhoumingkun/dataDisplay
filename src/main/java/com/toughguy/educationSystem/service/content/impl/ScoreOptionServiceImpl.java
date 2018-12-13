package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.persist.content.prototype.IScoreOptionDao;
import com.toughguy.educationSystem.service.content.prototype.IScoreOptionService;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 分值测试题选项Service实现类
 * @author zmk
 *
 */
@Service
public class ScoreOptionServiceImpl extends GenericServiceImpl<ScoreOption, Integer> implements IScoreOptionService{

	@Override
	public List<ScoreOption> findByTopicId(int topicId) {
		// TODO Auto-generated method stub
		return ((IScoreOptionDao)dao).findByTopicId(topicId);
	}
	

}
