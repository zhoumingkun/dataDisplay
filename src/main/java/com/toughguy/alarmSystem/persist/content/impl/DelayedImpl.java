package com.toughguy.alarmSystem.persist.content.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.persist.content.prototype.IDelayedDao;
import com.toughguy.alarmSystem.persist.impl.GenericDaoImpl;

@Repository
public class DelayedImpl extends GenericDaoImpl<Delayed, Integer> implements IDelayedDao{

	@Override
	public Delayed findOne(Delayed delayed) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne(typeNameSpace + ".findOne", delayed);
	}

	@Override
	public List<Delayed> selectAll(String time) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAll",time);
	}

}
