package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.persist.content.prototype.IDictXZQHBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 字典表-行政区划表Dao实现类
 * @author zmk
 *
 */
@Repository
public class DictXZQHBImpl extends GenericDaoImpl<DictXZQHB, Integer> implements IDictXZQHBDao{
	
	@Override
	public List<DictXZQHB> findAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAll");
	}

	
	
}
