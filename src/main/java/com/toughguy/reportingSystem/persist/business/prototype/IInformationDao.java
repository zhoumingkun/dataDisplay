package com.toughguy.reportingSystem.persist.business.prototype;

import java.util.Map;

import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 举报信息Dao接口类
 * @author BOBO
 *
 */
public interface IInformationDao  extends IGenericDao<Information, Integer>{

	/**
	 * 查询案件数量接口
	 * @param params
	 * @return
	 */
	public int findNum(int state);
}
