package com.toughguy.reportingSystem.service.business.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.prototype.IGenericService;

/**
 * 举报信息Service层接口类
 * @author BOBO
 *
 */
public interface IInformationService extends IGenericService<Information, Integer>{

	/**
	 * 查询案件数量接口
	 * @param state
	 * @return
	 */
	public int findNum(int state);
	/**
	 * 查询每日举报已接案件数量
	 * @return
	 */
	public List<Integer> findValidNumber();
	/**
	 * 查询每日举报总数
	 * @return
	 */
	public List<InformationDTO> findSum();
	/**
	 * 查询记录小程序查询
	 * @return
	 */
//	public List<Information> getInformation(String informerId);
	public List<Information> getInformation(int informerId);
	/**
	 * 查询各行业领域类型数量
	 */
	public Information findAllInformerType();
}
