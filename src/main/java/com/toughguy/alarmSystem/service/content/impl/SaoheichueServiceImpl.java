package com.toughguy.alarmSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.alarmSystem.model.content.Zhengcefagui;
import com.toughguy.alarmSystem.persist.content.prototype.IZhengcefaguiDao;
import com.toughguy.alarmSystem.service.content.prototype.IZhengcefaguiService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;


/**
 * 政策法规Service实现类
 * @author zmk
 *
 */
@Service
public class ZhengcefaguiServiceImpl extends GenericServiceImpl<Zhengcefagui, Integer> implements IZhengcefaguiService{
	/**
	 * 根据标题查询
	 * */
	@Override
 	public List<Zhengcefagui> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return ((IZhengcefaguiDao)dao).findByTitle(title);
 	}

}
