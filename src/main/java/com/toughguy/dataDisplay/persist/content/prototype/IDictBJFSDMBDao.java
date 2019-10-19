package com.toughguy.dataDisplay.persist.content.prototype;


import java.util.List;
import java.util.Map;

import com.toughguy.dataDisplay.model.content.DictBJFSDMB;
import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 字典表-报警方式代码表Dao接口类
 * @author zmk
 *
 */
public interface IDictBJFSDMBDao extends IGenericDao<DictBJFSDMB, Integer> {
	
	public List<DictBJFSDMB> findAll();
	
	
}
