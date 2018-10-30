package com.toughguy.reportingSystem.persist.business.impl;


import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.persist.business.prototype.IFeedbackInformationDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;

/**
 * 反馈信息Dao实现类
 * @author BOBO
 *
 */
@Repository
public class FeedbackInformationDaoImpl extends GenericDaoImpl<FeedbackInformation, Integer> implements IFeedbackInformationDao{


}
