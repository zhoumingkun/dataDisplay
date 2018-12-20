package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.educationSystem.model.authority.User;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 账户Service层接口类
 * @author zmk
 *
 */
public interface IAccountService extends IGenericService<Account, Integer>{
	/**
	 * 查询某题的危险学生列表
	 * @return
	 */
	public PagerModel<Account> findAllByRisk(Map<String, Object> params);
	
	public Account findByOpenId(String openId);
	/**
	 * 根据account查询学生
	 * @param account
	 * @return
	 */
	public Account findByAccount(String account);

}
