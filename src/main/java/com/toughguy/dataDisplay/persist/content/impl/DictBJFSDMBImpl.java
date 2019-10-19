package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.dataDisplay.model.content.DictBJFSDMB;
import com.toughguy.dataDisplay.persist.content.prototype.IDictBJFSDMBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 字典表-报警方式代码表Dao实现类
 * @author zmk
 *
 */
@Repository
public class DictBJFSDMBImpl extends GenericDaoImpl<DictBJFSDMB, Integer> implements IDictBJFSDMBDao{
	
	@Override
	public List<DictBJFSDMB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
