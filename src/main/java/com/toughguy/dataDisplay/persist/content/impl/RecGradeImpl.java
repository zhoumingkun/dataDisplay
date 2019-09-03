package com.toughguy.dataDisplay.persist.content.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.persist.content.prototype.IRecGradeDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;

@Repository
public class RecGradeImpl extends GenericDaoImpl<RecGrade, Integer> implements IRecGradeDao{

	@Override
	public void alterGrade(RecGrade grade) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update(typeNameSpace + ".alterGrade",grade);
	}

	@Override
	public List<RecGrade> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAll");
	}

}
