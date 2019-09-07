package com.toughguy.alarmSystem.persist.content.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.persist.prototype.IGenericDao;

/**
 * 扫黑除恶Dao接口类
 * @author zmk
 *
 */
public interface ISaoheichueDao extends IGenericDao<Saoheichue, Integer>{

	/**
	 * 省厅扫黑除恶柱状图
	 * @return
	 */
	public Saoheichue findAllSH(Map<String,String> map);

	/**
	 * 地级市扫黑除恶柱状图
	 * @return
	 */
	public Saoheichue findOneSH(Map<String,String> map);
	
	/**
	 * 省厅扫黑除恶表数据
	 * @return
	 */
	public List<Saoheichue> selectAll(String time);

	/**
	 * 地级市扫黑除恶表数据
	 * @return
	 */
	public Saoheichue selectOne(Map<String,String> map);
	
	/**
	 * 省厅扫黑除恶统计列表
	 * @return
	 */
	public List<Saoheichue> selectAllList(Map<String,String> map);
	
	
	/**
	 * 省厅扫黑除恶统计列表
	 * @return
	 */
	public List<Saoheichue> selectList(Map<String,String> map);

	
	/**
	 * 地级市扫黑除恶查询是否添加过数据
	 * @return
	 */
	public List<Saoheichue> findOne(Map<String,String> map);
	
	/**
	 * 地地级市扫黑除恶修改数据
	 * @return
	 */
	public void updateAll(Saoheichue saoheichue);
}
