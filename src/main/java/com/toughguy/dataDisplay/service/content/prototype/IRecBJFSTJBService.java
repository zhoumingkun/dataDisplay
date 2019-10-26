package com.toughguy.dataDisplay.service.content.prototype;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

/**
 * 统计表表-报警方式统计表 Service层接口类
 * @author zmk
 *
 */
public interface IRecBJFSTJBService extends IGenericService<RecBJFSTJB, Integer>{
	
	//查询今日报警方式 （首页）
	public List<RecBJFSTJB>  findBJFSShen(String tjTime);
	
	//查询报警方式七天全省 （首页）
	public List<RecBJFSTJB>  findBJFSSevenDayShen(String startTime,String endTime);
}
