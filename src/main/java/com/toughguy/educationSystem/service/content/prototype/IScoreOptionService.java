package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 分值测试题选项Service层接口类
 * @author zmk
 *
 */
public interface IScoreOptionService extends IGenericService<ScoreOption, Integer>{
	/**
	 * 根据题目id查询
	 * */
	public List<ScoreOption> findByTopicId(int topicId);
}
