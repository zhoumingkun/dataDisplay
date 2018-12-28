package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;
import java.util.Map;

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
	public int findTesterSum(Map<String, Object> params);
	/**
	 * 查询平台测试合格人次
	 * @return
	 */
	public int findTesterPassSum(Map<String, Object> params);
	/**
	 * 查询平台测试不合格人次
	 * @return
	 */
	public int findTesterFailureSum(Map<String, Object> params);
	/**
	 * 查询某个账号（学生）的危险测评题数
	 * @param id 学生账号id
	 * @return
	 */
	public int findRiskAssessment(int id);
	/**
	 * 根据用户id查询 
	 * @param id 学生账号id
	 * @return
	 */
	public List<AccountResult> findByAccountId(int id);
	
	/**
	 * 查询测试总人数（某题）
	 * @return
	 */
	public int findTesterSumByTestId(Map<String, Object> params);
	/**
	 * 查询平台测试合格人次（某题）
	 * @return
	 */
	public int findTesterPassSumByTestId(Map<String, Object> params);
	/**
	 * 查询平台测试不合格人次（某题）
	 * @return
	 */
	public int findTesterFailureSumByTestId(Map<String, Object> params);

}
