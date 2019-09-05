package com.toughguy.alarmSystem.service.content.prototype;
import java.util.List;
import java.util.Map;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.service.prototype.IGenericService;

/**
 * 报警情况Service层接口类
 * @author zmk
 *
 */
public interface IBaojingqingkuangService extends IGenericService<Baojingqingkuang, Integer>{
	/**
	 * 省厅报警情况柱状图
	 * @return
	 */
	public Map<String ,Baojingqingkuang> findAllBJ();

	/**
	 * 地级市报警情况柱状图
	 * @return
	 */
	public Map<String ,Baojingqingkuang> findOneBJ(String xzqh);
	   
	/**
	 * 地级市添加报警情况统计表
	 * @return
	 */
	public Map<String ,String> insertAll(List<Baojingqingkuang> list);

	/**
	 * 省厅查询报警情况统计表
	 * @return
	 */
	public List<Baojingqingkuang> selectAll(String starttime,String stoptime);
	
	/**
	 * 地级市查询报警情况统计表
	 * @return
	 */
	public List<Baojingqingkuang> selectOne(String starttime,String stoptime,String xzqh);
	
	
	
	/**
	 * 查询报警情况列表展示
	 * @return
	 */
	public  Map<String,Object> selectList(String time,String xzqh);
	
}
