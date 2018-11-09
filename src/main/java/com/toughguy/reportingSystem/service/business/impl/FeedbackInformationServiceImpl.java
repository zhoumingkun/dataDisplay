package com.toughguy.reportingSystem.service.business.impl;


import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.persist.business.prototype.IFeedbackInformationDao;
import com.toughguy.reportingSystem.service.business.prototype.IFeedbackInformationService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

/**
 * 反馈信息Service实现类
 * @author BOBO
 *
 */
@Service
public class FeedbackInformationServiceImpl extends GenericServiceImpl<FeedbackInformation, Integer> implements IFeedbackInformationService {

	@Override
	public FeedbackInformation findByType(String type) {
		// TODO Auto-generated method stub
		return ((IFeedbackInformationDao)dao).findByType(type);
	}


}
