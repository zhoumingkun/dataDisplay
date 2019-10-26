package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-接警类型统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecJJLXTJBDao extends IGenericDao<RecJJLXTJB, Integer> {
	
	//查询今日接警类型 （首页）
	public List<RecJJLXTJB>  findJJLXShen(String tjTime);
	
	//查询接警类型七天全省（首页）
	public List<RecJJLXTJB>  findJJLXSevenDayShen(Map<String,String> map);
	
	//查询时间区间的警情数据分析
	public List<RecJJLXTJB> findAlarmData(Map<String,String> map);
	
	//查询时间区间的警情数据分析(有行政区划)
	public List<RecJJLXTJB> findAlarmDataXZQH(Map<String,String> map);
	
	//根据时间区间查询地级市的警情数据分析
	public List<RecJJLXTJB> findCityAlarmData(Map<String,String> map);
}
