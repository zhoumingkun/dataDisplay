package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 分值级别表Service层接口类
 * @author zmk
 *
 */
public interface IScoreRankService extends IGenericService<ScoreRank, Integer>{
	
	/**
	 * 根据题id查询
	 * */
	public List<ScoreRank> findByTestId(int testId);
}
