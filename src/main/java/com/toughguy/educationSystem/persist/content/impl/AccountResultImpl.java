package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.persist.content.prototype.IAccountResultDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 用户与结果Dao实现类
 * @author zmk
 *
 */
@Repository
public class AccountResultImpl extends GenericDaoImpl<AccountResult, Integer> implements IAccountResultDao{

	@Override
	public int findTesterSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int count = sqlSessionTemplate.selectOne(typeNameSpace + ".findTesterSum",params);
		return count;
	}

	@Override
	public int findTesterPassSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int count = sqlSessionTemplate.selectOne(typeNameSpace + ".findTesterPassSum",params);
		return count;
	}

	@Override
	public int findTesterFailureSum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int count = sqlSessionTemplate.selectOne(typeNameSpace + ".findTesterFailureSum",params);
		return count;
	}

	@Override
	public int findRiskAssessment(int id) {
		// TODO Auto-generated method stub
		int count = sqlSessionTemplate.selectOne(typeNameSpace + ".findRiskAssessment",id);
		return count;
	}
	@Override
	public List<AccountResult> findByAccountId(int id) {
		// TODO Auto-generated method stub
		 List<AccountResult> ars = sqlSessionTemplate.selectList(typeNameSpace + ".findByAccountId",id);
		return ars;
	}


}
