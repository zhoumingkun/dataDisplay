package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 信息Dao接口类
 * @author zmk
 *
 */
public interface IContentDao extends IGenericDao<Content, Integer>{
	
	/**
	 * 根据内容类型查询
	 * */
	public Content findByType(int type);
	

}
