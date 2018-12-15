package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.persist.content.prototype.ISizhengjiansheDao;
import com.toughguy.educationSystem.service.content.prototype.ISizhengjiansheService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 思政建设Service实现类
 * @author zmk
 *
 */
@Service
public class SizhengjiansheServiceImpl extends GenericServiceImpl<Sizhengjianshe, Integer> implements ISizhengjiansheService{
	/**
	 * 根据标题查询
	 * */
	@Override
 	public List<Sizhengjianshe> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return ((ISizhengjiansheDao)dao).findByTitle(title);
 	}

	@Override
	public List<Sizhengjianshe> findBySource(String articleSource) {
		// TODO Auto-generated method stub
		return ((ISizhengjiansheDao)dao).findBySource(articleSource);
	}

}
