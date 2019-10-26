package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;

import com.toughguy.dataDisplay.model.content.DictBJFSDMB;
import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-报警方式统计表 Dao接口类
 * @author zmk
 *
 */
public interface IRecBJFSTJBDao extends IGenericDao<RecBJFSTJB, Integer> {
	
	//查询今日报警方式 （首页）
	public List<RecBJFSTJB>  findBJFSShen(String tjTime);
		
	//查询报警方式七天全省 （首页）
	public List<RecBJFSTJB>  findBJFSSevenDayShen(Map<String,String> map);
	
}
