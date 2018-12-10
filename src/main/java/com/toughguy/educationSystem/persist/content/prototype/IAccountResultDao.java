package com.toughguy.educationSystem.persist.content.prototype;

import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 *用户与结果Dao接口类
 *
 */
public interface IAccountResultDao extends IGenericDao<AccountResult, Integer>{
	
	/**
	 * 查询平台测试总人数
	 * @return
	 */
	public int findTesterSum();
	/**
	 * 查询平台测试合格人次
	 * @return
	 */
	public int findTesterPassSum();
	/**
	 * 查询平台测试不合格人次
	 * @return
	 */
	public int findTestFailureSum();

}
