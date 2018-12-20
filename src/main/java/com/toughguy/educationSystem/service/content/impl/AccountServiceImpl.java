package com.toughguy.educationSystem.service.content.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.authority.User;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.persist.content.prototype.IAccountDao;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;
import com.toughguy.educationSystem.util.MD5Util;


/**
 * 账户Service实现类
 * @author zmk
 *
 */
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, Integer> implements IAccountService{
	
	//-- 对用户密码进行加密后，再保存
	@Override
	public void save(Account entity) {
		entity.setPassword(new DefaultPasswordService().encryptPassword(entity.getPassword()));
		super.save(entity);
	}
	
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

	@Override
	public Account findByAccount(String account) {
		// TODO Auto-generated method stub
		return ((IAccountDao)dao).findByAccount(account);
	}

}
