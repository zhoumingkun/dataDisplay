package com.toughguy.dataDisplay.persist.content.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecBJFSTJBDao;
import com.toughguy.dataDisplay.persist.impl.GenericDaoImpl;
/**
 * 统计表表-报警方式统计表 Dao实现类
 * @author zmk
 *
 */
@Repository
public class RecBJFSTJBImpl extends GenericDaoImpl<RecBJFSTJB, Integer> implements IRecBJFSTJBDao{
	
	@Override
	public List<RecBJFSTJB> findBJFSShen(String tjTime) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findBJFSShen",tjTime);
	}

	@Override
	public List<RecBJFSTJB> findBJFSSevenDayShen(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findBJFSSevenDayShen",map);
	}


	@Override
	public List<RecBJFSTJB> findSAlarmModeXZQH(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findSAlarmModeXZQH",map);
	}

	@Override
	public List<RecBJFSTJB> findCityAlarmMode(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findCityAlarmMode",map);
	}
	
}
