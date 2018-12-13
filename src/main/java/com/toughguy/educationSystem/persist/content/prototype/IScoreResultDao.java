package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.model.content.ScoreResult;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 分值测试结果Dao接口类
 * @author zmk
 *
 */
public interface IScoreResultDao extends IGenericDao<ScoreResult, Integer>{
	
	
	/**
	 * 根据题id查询
	 * */
	public ScoreResult findByTestId(int testId);
}
