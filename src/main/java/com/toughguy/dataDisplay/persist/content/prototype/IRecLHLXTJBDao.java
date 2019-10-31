package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;

import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-来话类型统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecLHLXTJBDao extends IGenericDao<RecLHLXTJB, Integer> {
	
	//查询今日来话类型 （首页）
	public List<RecLHLXTJB>  findLHLXShen(String tjTime);
			
	//查询来话类型七天全省 （首页）
	public List<RecLHLXTJB>  findLHLXSevenDayShen(Map<String,String> map);
	
	//查询来话类型全省数据 （有行政区划）
	public List<RecLHLXTJB>  findSIncomingTypeXZQH(Map<String,String> map);
		
	//查询来话类型地级市数据 
	public List<RecLHLXTJB>  findCityIncomingType(Map<String,String> map);
}
