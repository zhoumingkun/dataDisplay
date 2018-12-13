package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.persist.content.prototype.IScoreRankDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 分值级别表Dao实现类
 * @author zmk
 *
 */
@Repository
public class ScoreRankImpl extends GenericDaoImpl<ScoreRank, Integer> implements IScoreRankDao{

	@Override
	public List<ScoreRank> findByTestId(int testId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTestId",testId);
	}
	

}
