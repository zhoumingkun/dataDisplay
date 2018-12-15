package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 思政建设Service层接口类
 * @author zmk
 *
 */
public interface ISizhengjiansheService extends IGenericService<Sizhengjianshe, Integer>{
	/**
 	 * 根据标题查询
 	 * */
 	public List<Sizhengjianshe> findByTitle(String title);
 	/**
 	 * 根据标来源查询
 	 * */
 	public List<Sizhengjianshe> findBySource(String articleSource);
}
