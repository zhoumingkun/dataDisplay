package com.toughguy.educationSystem.persist.content.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.persist.content.prototype.IAccountDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
import com.toughguy.educationSystem.system.SystemContext;
/**
 * 账户Dao实现类
 * @author zmk
 *
 */
@Repository
public class AccountImpl extends GenericDaoImpl<Account, Integer> implements IAccountDao{

	@Override
	public PagerModel<Account> findAllByRisk(Map<String, Object> params) {
		// TODO Auto-generated method stub
		// -- 1. 不管传或者不传参数都会追加至少两个分页参数
				if (params == null)
					params = new HashMap<String, Object>();
				params.put("offset", SystemContext.getOffset());
				params.put("limit", SystemContext.getPageSize());
				PagerModel<Account> pm = new PagerModel<Account>();
				int total = getRiskTotalNumOfItems(params);
				List<Account> entitys = sqlSessionTemplate.selectList(typeNameSpace + ".findAllByRisk", params);
				pm.setTotal(total);
				pm.setData(entitys);
				return pm;
	}
	// -- 获取总的条目数 (分页查询中使用)
	private int getRiskTotalNumOfItems(Map<String, Object> params) {
		int count = (Integer) sqlSessionTemplate.selectOne(typeNameSpace + ".getRiskTotalOfItems", params);
		return count;
	}
	
	@Override
	public Account findByOpenId(String openId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByOpenId", openId);
	}
	@Override
	public Account findByAccount(String account) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByAccount",account);
	}
	

}
