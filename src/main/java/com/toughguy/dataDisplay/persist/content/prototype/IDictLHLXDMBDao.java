package com.toughguy.dataDisplay.persist.content.prototype;
import java.util.List;
import java.util.Map;
import com.toughguy.dataDisplay.model.content.DictLHLXDMB;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

/**
 * 字典表-来话类型代码表 Dao接口类
 * @author zmk
 *
 */
public interface IDictLHLXDMBDao extends IGenericDao<DictLHLXDMB, Integer> {
	
	public List<DictLHLXDMB> findAll();
	
	
}
