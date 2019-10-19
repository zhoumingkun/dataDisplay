package com.toughguy.dataDisplay.persist.content.prototype;


import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 字典表-行政区划表Dao接口类
 * @author zmk
 *
 */
public interface IDictXZQHBDao extends IGenericDao<DictXZQHB, Integer> {
	
	public List<DictXZQHB> findAll();
	
	
}
