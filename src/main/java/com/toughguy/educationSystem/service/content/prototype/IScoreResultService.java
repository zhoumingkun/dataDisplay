package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.model.content.ScoreResult;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 分值测试结果Service层接口类
 * @author zmk
 *
 */
public interface IScoreResultService extends IGenericService<ScoreResult, Integer>{
	/**
	 * 根据题id查询
	 * */
	public ScoreResult findByTestId(int testId);
}
