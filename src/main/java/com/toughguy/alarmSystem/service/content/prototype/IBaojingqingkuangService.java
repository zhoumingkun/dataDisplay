package com.toughguy.alarmSystem.service.content.prototype;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
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
	 * 省厅查询报警情况统计表
	 * @return
	 */
	public List<Baojingqingkuang> selectAll(String time);
	
	/**
	 * 地级市查询报警情况统计表
	 * @return
	 */
	public List<Baojingqingkuang> selectOne(String time,String xzqh);
	
	
	
	/**
	 * 查询报警情况列表展示
	 * @return
	 */
	public  Map<String,Object> selectList(String time,String xzqh);
	
	
	/**
	 * 导出省报警情况表
	 * @return
	 */
	public void ExportShenBjqk(HttpServletResponse response,String tjyf);
	/**
	 * 导出市报警情况表
	 * @return
	 */
	public void ExportShiBjqk(HttpServletResponse response, String tjyf, String xzqh);

	
	
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
	
}
