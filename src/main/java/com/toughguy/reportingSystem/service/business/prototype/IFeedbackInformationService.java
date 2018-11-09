package com.toughguy.reportingSystem.service.business.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.service.prototype.IGenericService;

/**
 * 反馈信息Service层接口类
 * @author BOBO
 *
 */
public interface IFeedbackInformationService extends IGenericService<FeedbackInformation, Integer>{
	/**
	 * 根据反馈原因查询反馈信息
	 * @param type
	 * @return
	 */
	public FeedbackInformation findByType(String type);
}
