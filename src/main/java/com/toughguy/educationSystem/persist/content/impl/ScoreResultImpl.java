package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.ScoreResult;
import com.toughguy.educationSystem.persist.content.prototype.IScoreResultDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 分值测试结果Dao实现类
 * @author zmk
 *
 */
@Repository
public class ScoreResultImpl extends GenericDaoImpl<ScoreResult, Integer> implements IScoreResultDao{

	@Override
	public ScoreResult findByTestId(int testId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByTestId",testId);
	}
	

}
