package com.toughguy.alarmSystem.persist.content.prototype;


import java.util.List;
import java.util.Map;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.persist.prototype.IGenericDao;

/**
 * 报警情况Dao接口类
 * @author zmk
 *
 */
public interface IBaojingqingkuangDao extends IGenericDao<Baojingqingkuang, Integer>{
	
	public Baojingqingkuang findAllBJ(Map<String,String> map);
	
	public Baojingqingkuang findOneBJ(Map<String,String> map);
	
	public List<Baojingqingkuang> selectAll(Map<String,String> map );
	
	public List<Baojingqingkuang> selectOne(Map<String,String> map);
	
	//选了时间  选了地市(没选时间  选了地市)
	public List<Baojingqingkuang> selectList(Map<String,String> map);
	
	//选了时间  没选地市(没选时间  没选地市 )
	public List<Baojingqingkuang> selectAllList(Map<String,String> map);
}
