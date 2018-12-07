package com.toughguy.educationSystem.service.content.impl;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Activity;
import com.toughguy.educationSystem.persist.content.prototype.IActivityDao;
import com.toughguy.educationSystem.service.content.prototype.IActivityService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 校园活动Service实现类
 * @author zmk
 *
 */
@Service
public class ActivityServiceImpl extends GenericServiceImpl<Activity, Integer> implements IActivityService{
	/**
	 * 查询最新的活动动态消息
	 * */
	@Override
	public Activity findNew() {
		// TODO Auto-generated method stub
		return ((IActivityDao)dao).findNew();
	}
	

}
