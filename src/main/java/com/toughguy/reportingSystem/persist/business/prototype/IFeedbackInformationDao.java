package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 反馈信息Dao接口类
 * @author BOBO
 *
 */
public interface IFeedbackInformationDao  extends IGenericDao<FeedbackInformation, Integer>{
	
	/**
	 * 根据反馈原因查询反馈信息
	 * @param type
	 * @return
	 */
	public FeedbackInformation findByType(String type);
}
