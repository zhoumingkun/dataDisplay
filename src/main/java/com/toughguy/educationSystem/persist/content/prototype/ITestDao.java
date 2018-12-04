package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 心理测试试题Dao接口类
 * @author zmk
 *
 */
public interface ITestDao extends IGenericDao<Test, Integer>{
	/**
	 * 根据类型查询
	 * */
	public List<Test>findByType(String type);

}
