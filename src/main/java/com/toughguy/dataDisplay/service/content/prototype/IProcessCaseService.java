package com.toughguy.dataDisplay.service.content.prototype;

import java.util.Map;

import com.toughguy.dataDisplay.model.content.ProcessCase;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

public interface IProcessCaseService extends IGenericService<ProcessCase, Integer> {

	//查询全流程执法的数据
	public Map<String,Object> findProcessCase();

	//查询启邦首页立案总数环比百分比
	public Map<String, Object> findProcessCaseHB(String tjTime);
}
