package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-报警方式统计表 Dao接口类
 * @author zmk
 *
 */
public interface IRecBJFSTJBDao extends IGenericDao<RecBJFSTJB, Integer> {
	
	public List<RecBJFSTJB> findAll();
	
	
}
