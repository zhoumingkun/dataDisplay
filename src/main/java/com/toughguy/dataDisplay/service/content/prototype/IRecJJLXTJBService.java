package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-接警类型统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecJJLXTJBService extends IGenericService<RecJJLXTJB, Integer>{
	
	//查询今日接警类型 （首页）
	public List<RecJJLXTJB>  findJJLXShen(String tjTime);
	
	//查询接警类型七天全省（首页）
	public Map<String, Object>  findJJLXSevenDayShen(String startTime,String endTime);
	
	//根据时间区间查询省的警情数据分析
	public Map<String, Object> findSAlarmData(String startTime, String endTime);

	//根据时间区间查询地级市的警情数据分析
	public Map<String, Object> findCityAlarmData(String startTime, String endTime, String xzqhdm);
}
