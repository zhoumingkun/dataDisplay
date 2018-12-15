package com.toughguy.educationSystem.service.content.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.persist.content.prototype.IXiaoyuanhuangyeDepartmentDao;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeDepartmentService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;


/**
 * 校园黄页Service实现类
 * @author zmk
 *
 */
@Service
public class XiaoyuanhuangyeDepartmentServiceImpl extends GenericServiceImpl<XiaoyuanhuangyeDepartment, Integer> implements IXiaoyuanhuangyeDepartmentService{

	@Override
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id) {
		// TODO Auto-generated method stub
		return ((IXiaoyuanhuangyeDepartmentDao)dao).findAllDepartment(id);
	}

}
