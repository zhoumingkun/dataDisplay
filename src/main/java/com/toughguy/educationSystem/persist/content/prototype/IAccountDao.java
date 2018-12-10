package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 *账户Dao接口类
 *
 */
public interface IAccountDao extends IGenericDao<Account, Integer>{
	
	/**
	 * 查询某题的危险学生列表
	 * @return
	 */
	public PagerModel<Account> findAllByRisk(Map<String, Object> params);
}
