package com.toughguy.educationSystem.service.content.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.persist.content.prototype.IAccountResultDao;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 用户与结果Service实现类
 * @author zmk
 *
 */
@Service
public class AccountResultServiceImpl extends GenericServiceImpl<AccountResult, Integer> implements IAccountResultService{

	@Override
	public int findTesterSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ((IAccountResultDao)dao).findTesterSum(params);
	}

	@Override
	public int findTesterPassSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ((IAccountResultDao)dao).findTesterPassSum(params);
	}

	@Override
	public int findTesterFailureSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ((IAccountResultDao)dao).findTesterFailureSum(params);
	}

	@Override
	public int findRiskAssessment(int id) {
		// TODO Auto-generated method stub
		return ((IAccountResultDao)dao).findRiskAssessment(id);
	}

	@Override
	public List<AccountResult> findByAccountId(int id) {
		// TODO Auto-generated method stub
		return ((IAccountResultDao)dao).findByAccountId(id);
	}


}
