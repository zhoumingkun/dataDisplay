package com.toughguy.dataDisplay.persist.content.prototype;


import java.util.List;

import com.toughguy.dataDisplay.model.content.ProcessCase;
import com.toughguy.dataDisplay.persist.prototype.IGenericDao;

public interface IProcessCaseDao extends IGenericDao<ProcessCase, Integer> {
	
	//查询当天立案总数
	public ProcessCase findTotal(String lasj);

	//查询案件类别
	public List<ProcessCase> findCaseCategory(String lasj);
	
	//查询地级市的立案数
	public List<ProcessCase> findCityCaseNum(String lasj);

}
