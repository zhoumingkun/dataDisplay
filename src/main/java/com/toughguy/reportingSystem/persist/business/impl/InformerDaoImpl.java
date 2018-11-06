package com.toughguy.reportingSystem.persist.business.impl;

import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.persist.business.prototype.IInformerDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;

/**
 * 举报信息Dao实现类
 * @author BOBO
 *
 */
@Repository
public class InformerDaoImpl extends GenericDaoImpl<Informer, Integer> implements IInformerDao{
	/**
	 * 个人信息获取
	 *
	 */
	@Override
	public Informer getInformer(String openId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".getInformer", openId);
	}

}
