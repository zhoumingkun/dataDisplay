package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-接警类型统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecJJLXTJBDao extends IGenericDao<RecJJLXTJB, Integer> {
	
	public List<RecJJLXTJB> findAll();
	
	
}
