package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 统计表表-来话类型统计表  Dao接口类
 * @author zmk
 *
 */
public interface IRecLHLXTJBDao extends IGenericDao<RecLHLXTJB, Integer> {
	
	public List<RecLHLXTJB> findAll();
	
	
}
