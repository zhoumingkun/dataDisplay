package com.toughguy.educationSystem.service.content.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.persist.content.prototype.IXiaoyuanhuangyeDao;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 校园黄页Service实现类
 * @author zmk
 *
 */
@Service
public class XiaoyuanhuangyeServiceImpl extends GenericServiceImpl<Xiaoyuanhuangye, Integer> implements IXiaoyuanhuangyeService{
	/**
	 * 根据部门名称查询
	 * */
	@Override
	public List<Xiaoyuanhuangye> findBySectionName(String sectionName) {
		// TODO Auto-generated method stub
		return ((IXiaoyuanhuangyeDao)dao).findBySectionName(sectionName);	
	}
	/**
	 * 根据类型查询部门名称
	 * 
	 * */
	@Override
	public Xiaoyuanhuangye findSectionNameByType(int type) {
		// TODO Auto-generated method stub
		return ((IXiaoyuanhuangyeDao)dao).findSectionNameByType(type);	
	}
	

}
