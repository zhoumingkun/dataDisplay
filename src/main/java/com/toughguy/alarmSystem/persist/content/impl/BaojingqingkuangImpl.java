package com.toughguy.alarmSystem.persist.content.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.persist.content.prototype.IBaojingqingkuangDao;
import com.toughguy.alarmSystem.persist.impl.GenericDaoImpl;
/**
 * 报警情况Dao实现类
 * @author zmk
 *
 */
@Repository
public class BaojingqingkuangImpl extends GenericDaoImpl<Baojingqingkuang, Integer> implements IBaojingqingkuangDao{
	@Override
	public Baojingqingkuang findAllBJ(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findAllBJ", map);
	}

	@Override
	public Baojingqingkuang findOneBJ(Map<String,String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findOneBJ", map);
	}

	@Override
	public List<Baojingqingkuang> selectAll(String time) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAll", time);
	}

	@Override
	public List<Baojingqingkuang> selectOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectOne", map);
	}

	@Override
	public List<Baojingqingkuang> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectList", map);
	}

	@Override
	public List<Baojingqingkuang> selectAllList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".selectAllList", map);
	}
	
	//导出省报警情况表
	@Override
	public List<Baojingqingkuang> findByTjyfTime(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTjyfTime", map);
	}
	
	//导出省报警情况表（合计）
		@Override
		public Baojingqingkuang findShenHj(String tjyf) {
			// TODO Auto-generated method stub
			return sqlSessionTemplate.selectOne(typeNameSpace + ".findShenHj", tjyf);
		}
		
	
	//导出市报警情况表
		@Override
	public List<Baojingqingkuang> findByTjyfAndRegion(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTjyfAndRegion", map);
		}
		
	//导出省报警情况表（合计）
		@Override
	public Baojingqingkuang findShiHj(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findShiHj", map);
		}

		@Override
		public List<Baojingqingkuang> findOne(Map<String, String> map) {
			// TODO Auto-generated method stub
			return sqlSessionTemplate.selectList(typeNameSpace + ".findOne", map);
		}

		@Override
		public void updateAll(Baojingqingkuang baojingqingkuang) {
			// TODO Auto-generated method stub
			sqlSessionTemplate.update(typeNameSpace + ".updateAll", baojingqingkuang);
		}
	
	
}
