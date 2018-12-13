package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 分值级别表Dao接口类
 * @author zmk
 *
 */
public interface IScoreRankDao extends IGenericDao<ScoreRank, Integer>{
	
	/**
	 * 根据题id查询
	 * */
	public List<ScoreRank> findByTestId(int testId);

}
