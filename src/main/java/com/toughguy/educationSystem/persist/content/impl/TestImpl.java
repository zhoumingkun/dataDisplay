package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.persist.content.prototype.ITestDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 心理测试试题Dao实现类
 * @author zmk
 *
 */
@Repository
public class TestImpl extends GenericDaoImpl<Test, Integer> implements ITestDao{
	/**
	 * 根据类型查询
	 * 
	 * */
	@Override
	public List<Test> findByType(String type) {		
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByType", type);
		
	}

	@Override
	public List<Test> findByTitle(String title) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTitle", title);
	}

	@Override
	public int findTestSum() {
		// TODO Auto-generated method stub
		int count = (Integer) sqlSessionTemplate.selectOne(typeNameSpace + ".findTestSum");
		return count;
	}

}
