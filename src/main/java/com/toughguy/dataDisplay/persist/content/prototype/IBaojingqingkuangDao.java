package com.toughguy.dataDisplay.persist.content.prototype;


import java.util.List;
import java.util.Map;

import com.toughguy.dataDisplay.model.content.Baojingqingkuang;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 报警情况Dao接口类
 * @author zmk
 *
 */
public interface IBaojingqingkuangDao extends IGenericDao<Baojingqingkuang, Integer>{
	
	public Baojingqingkuang findAllBJ(Map<String,String> map);
	
	public Baojingqingkuang findOneBJ(Map<String,String> map);
	
	public List<Baojingqingkuang> selectAll(String time);
	
	public List<Baojingqingkuang> selectOne(Map<String,String> map);
	
	//选了时间  选了地市(没选时间  选了地市)
	public List<Baojingqingkuang> selectList(Map<String,String> map);
	
	//选了时间  没选地市(没选时间  没选地市 )
	public List<Baojingqingkuang> selectAllList(Map<String,String> map);
	
	//导出省报警情况报表
	public List<Baojingqingkuang> findByTjyfTime(Map<String,String> map);
	
	//导出省报警情况报表（合计）
	public Baojingqingkuang  findShenHj(String tjyf);
		
	//导出市报警情况报表
	public List<Baojingqingkuang> findByTjyfAndRegion(Map<String,String> map);
	
	//导出市报警情况报表（合计）
	public Baojingqingkuang  findShiHj(Map<String,String> map);

	/**
	 * 地级市报警情况查询是否添加过数据
	 * @return
	 */
	public List<Baojingqingkuang> findOne(Map<String,String> map);
	
	/**
	 * 地级市报警情况修改数据
	 * @return
	 */
	public void updateAll(Baojingqingkuang baojingqingkuang);
	
	/**
	 * 省厅开关数据抽取开关
	 * @param state
	 */
	public void etlSwitch(String state);
	
	/**
	 * 查询省厅数据抽取开关
	 * @param state
	 */
	public String findSwitch();
	
	/**
	 * 根据ip查询数据库该ip是否合法
	 * @param state
	 */
	public String findIP(String ip);
	
}
