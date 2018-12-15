package com.toughguy.educationSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.persist.content.prototype.IXiaoyuanhuangyeDepartmentDao;
import com.toughguy.educationSystem.persist.impl.GenericDaoImpl;
/**
 * 校园黄页部门Dao实现类
 * @author zmk
 *
 */
@Repository
public class XiaoyuanhuangyeDepartmentImpl extends GenericDaoImpl<XiaoyuanhuangyeDepartment, Integer> implements IXiaoyuanhuangyeDepartmentDao{

	@Override
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findAllDepartment",id);
	}
	
}
