package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.persist.content.prototype.ITestDao;
import com.toughguy.educationSystem.service.content.prototype.ITestService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 心理测试题Service实现类
 * @author zmk
 *
 */
@Service
public class TestServiceImpl extends GenericServiceImpl<Test, Integer> implements ITestService{
	/**
	 * 根据类型查询
	 * */
	@Override
	public List<Test> findByType(String type) {
		// TODO Auto-generated method stub
		return ((ITestDao)dao).findByType(type);	
	}

}
