package com.toughguy.dataDisplay.persist.content.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.dataDisplay.model.content.ProcessCase;
import com.toughguy.dataDisplay.persist.content.prototype.IProcessCaseDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;

@Repository
public class ProcessCaseImpl extends GenericDaoImpl<ProcessCase, Integer> implements IProcessCaseDao{

	@Override
	public ProcessCase findTotal(String lasj) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findTotal",lasj);
	}

	@Override
	public List<ProcessCase> findCaseCategory(String lasj) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findCaseCategory",lasj);
	}

	@Override
	public List<ProcessCase> findCityCaseNum(String lasj) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findCityCaseNum",lasj);
	}

}
