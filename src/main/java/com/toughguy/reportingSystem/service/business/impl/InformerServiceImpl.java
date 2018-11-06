package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.persist.business.prototype.IInformerDao;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

/**
 * 举报人Service实现类
 * @author BOBO
 *
 */
@Service
public class InformerServiceImpl extends GenericServiceImpl<Informer, Integer> implements IInformerService {
	/**
	 * 个人信息获取
	 */
	@Override
	public Informer getInformer(String openId) {
		// TODO Auto-generated method stub
		return ((IInformerDao)dao).getInformer(openId);
	}
	
}
