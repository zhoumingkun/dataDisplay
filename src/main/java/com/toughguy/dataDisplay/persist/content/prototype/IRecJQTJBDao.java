package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-警情统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecJQTJBDao extends IGenericDao<RecJQTJB, Integer> {
	
	//查询警情统计监测 （今日首页）
	public List<RecJQTJB>  findJQNum(String tjTime);
	
	//查询近七天警情统计（首页）
	public List<RecJQTJB>  findJQSevenDayShen(Map<String,String> map);
	
	//查询各行政区划警情数量（当日地图）
	public List<RecJQTJB>  findXZQHNum(String tjTime);
	
	
	//查询各行政区划警情数量环比
	public List<RecJQTJB>  findNumHB(String tjTime);
		
	//查询全省警情数量环比
	public List<RecJQTJB>  findXZQHNumHB(Map<String,String> map);
}
