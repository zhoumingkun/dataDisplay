package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-警情分类统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecJQFLTJBDao extends IGenericDao<RecJQFLTJB, Integer> {
	
	public List<RecJQFLTJB> findAll();
	
	
}
