package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.persist.content.prototype.ISizhengjiansheDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 思政建设Dao实现类
 * @author zmk
 *
 */
@Repository
public class SizhengjiansheImpl extends GenericDaoImpl<Sizhengjianshe, Integer> implements ISizhengjiansheDao{
	/**
	 * 根据标题查询
	 * 
	 * */
	@Override
 	public List<Sizhengjianshe> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTitle", title);
 	}

	@Override
	public List<Sizhengjianshe> findBySource(String source) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findBySource", source);
	}

}
