package com.toughguy.alarmSystem.persist.content.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.persist.content.prototype.ISaoheichueDao;
import com.toughguy.alarmSystem.persist.impl.GenericDaoImpl;
/**
 * 扫黑除恶Dao实现类
 * @author zmk
 *
 */
@Repository
public class SaoheichueImpl extends GenericDaoImpl<Saoheichue, Integer> implements ISaoheichueDao{

	@Override
	public Saoheichue findAllSH(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findAllSH", map);
	}

	@Override
	public Saoheichue findOneSH(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findOneSH", map);
	}

	@Override
	public List<Saoheichue> selectAllList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAllList", map);
	}

	@Override
	public List<Saoheichue> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectList", map);
	}

	@Override
	public List<Saoheichue> selectAll(String time) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAll", time);
	}

	@Override
	public Saoheichue selectOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".selectOne", map);
	}

	@Override
	public List<Saoheichue> findOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findOne", map);
	}

	@Override
	public void updateAll(Saoheichue saoheichue) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update(typeNameSpace + ".updateAll", saoheichue);
	}

}
