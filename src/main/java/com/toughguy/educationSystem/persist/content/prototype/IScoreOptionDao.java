package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 分值测试题选项Dao接口类
 * @author zmk
 *
 */
public interface IScoreOptionDao extends IGenericDao<ScoreOption, Integer>{
	
	/**
	 * 根据题目id查询
	 * */
	public List<ScoreOption> findByTopicId(int topicId);
}
