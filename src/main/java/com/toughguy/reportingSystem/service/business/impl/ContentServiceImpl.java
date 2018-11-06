package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.Content;
import com.toughguy.reportingSystem.persist.business.prototype.IContentDao;
import com.toughguy.reportingSystem.service.business.prototype.IContentService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;


/**
 * 信息Service实现类
 * @author zmk
 *
 */
@Service
public class ContentServiceImpl extends GenericServiceImpl<Content, Integer> implements IContentService{
	
	@Override
	public Content findByType(int type) {
		// TODO Auto-generated method stub
		return ((IContentDao)dao).findByType(type);	
	}

}
