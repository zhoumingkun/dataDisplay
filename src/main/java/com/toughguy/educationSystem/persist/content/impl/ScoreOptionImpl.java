package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.persist.content.prototype.IScoreOptionDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 分值测试题选项Dao实现类
 * @author zmk
 *
 */
@Repository
public class ScoreOptionImpl extends GenericDaoImpl<ScoreOption, Integer> implements IScoreOptionDao{

	@Override
	public List<ScoreOption> findByTopicId(int topicId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTopicId",topicId);
	}
	

}
