package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 单题测试题选项Service层接口类
 * @author zmk
 *
 */
public interface ISingleOptionService extends IGenericService<SingleOption, Integer>{
	/**
	 * 根据题目id查询选项
	 * @param topicId
	 * @return
	 */
	public List<SingleOption> findByTopicId(int topicId);
}
