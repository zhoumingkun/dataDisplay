package com.toughguy.educationSystem.service.content.prototype;

import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 用户与结果Service层接口类
 * @author zmk
 *
 */
public interface IAccountResultService extends IGenericService<AccountResult, Integer>{
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
