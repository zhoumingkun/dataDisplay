package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 单题测试题选项Dao接口类
 * @author zmk
 *
 */
public interface ISingleOptionDao extends IGenericDao<SingleOption, Integer>{
	
	/**
	 * 根据题目id查询选项
	 * @param topicId
	 * @return
	 */
	public List<SingleOption> findByTopicId(int topicId);
}
