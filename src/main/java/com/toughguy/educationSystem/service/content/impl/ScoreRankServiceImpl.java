package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.persist.content.prototype.IScoreRankDao;
import com.toughguy.educationSystem.service.content.prototype.IScoreRankService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 分值级别表Service实现类
 * @author zmk
 *
 */
@Service
public class ScoreRankServiceImpl extends GenericServiceImpl<ScoreRank, Integer> implements IScoreRankService{

	@Override
	public List<ScoreRank> findByTestId(int testId) {
		// TODO Auto-generated method stub
		return ((IScoreRankDao)dao).findByTestId(testId);
	}
	

}
