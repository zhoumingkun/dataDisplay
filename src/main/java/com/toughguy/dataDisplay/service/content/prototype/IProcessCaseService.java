package com.toughguy.dataDisplay.service.content.prototype;

import java.util.Map;

import com.toughguy.dataDisplay.model.content.ProcessCase;
import com.toughguy.dataDisplay.service.prototype.IGenericService;

public interface IProcessCaseService extends IGenericService<ProcessCase, Integer> {

	//查询全流程执法的数据
	public Map<String,Object> findProcessCase();
}
