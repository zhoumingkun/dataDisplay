package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-警情统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecJQTJBService extends IGenericService<RecJQTJB, Integer>{
	
	    //警情统计监测
		public List<RecJQTJB>  findJQNum(String tjTime);
		
		//查询近期警情统计全省七天（首页）
		public List<RecJQTJB>  findJQSevenDayShen(String startTime,String endTime);
		
		//警情统计监测各行政区划数量（首页地图用）
		public List<RecJQTJB>  findXZQHNum(String tjTime);
}
