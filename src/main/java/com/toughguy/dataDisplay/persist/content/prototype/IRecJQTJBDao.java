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
	
	//警情统计监测
	public List<RecJQTJB>  findJQNum(String tjTime);
	
	//查询近期警情统计全省七天（首页）
	public List<RecJQTJB>  findJQSevenDayShen(Map<String,String> map);
	
	//警情统计监测各行政区划数量（首页地图用）
	public List<RecJQTJB>  findXZQHNum(String tjTime);
}
