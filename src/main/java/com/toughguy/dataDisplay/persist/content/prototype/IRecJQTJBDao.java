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
	
	public List<RecJQTJB> findAll();
	
	
}
