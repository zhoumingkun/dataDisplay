package com.toughguy.reportingSystem.service.business.prototype;

import java.util.Map;

import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.service.prototype.IGenericService;

/**
 * 举报信息Service层接口类
 * @author BOBO
 *
 */
public interface IInformationService extends IGenericService<Information, Integer>{

	/**
	 * 查询案件数量接口
	 * @param params
	 * @return
	 */
	public int findNum(int state);
}
