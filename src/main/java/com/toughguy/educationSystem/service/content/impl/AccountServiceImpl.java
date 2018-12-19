package com.toughguy.educationSystem.service.content.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.persist.content.prototype.IAccountDao;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 账户Service实现类
 * @author zmk
 *
 */
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, Integer> implements IAccountService{

	@Override
	public PagerModel<Account> findAllByRisk(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ((IAccountDao)dao).findAllByRisk(params);
	}
	@Override
	public Account findByOpenId(String openId) {
		// TODO Auto-generated method stub
		return ((IAccountDao)dao).findByOpenId(openId);
	}

}
