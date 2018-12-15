package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeOrganization;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 心理测试题Service层接口类
 * @author zmk
 *
 */
public interface ITestService extends IGenericService<Test, Integer>{
	/**
	 * 根据部门名称查询
	 * */
	public List<Test>findByType(String type);
	/**
	 * 根据标题查询
	 * */
	public Test findByTitle(String title);
	/**
	 * 查询平台总题数 
	 * */
	public int findTestSum();
}
