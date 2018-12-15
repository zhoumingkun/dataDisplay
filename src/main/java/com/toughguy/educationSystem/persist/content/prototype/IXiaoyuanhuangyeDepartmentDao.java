package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 校园黄页部门Dao接口类
 * @author zmk
 *
 */
public interface IXiaoyuanhuangyeDepartmentDao extends IGenericDao<XiaoyuanhuangyeDepartment, Integer>{
	/**
	 * 根据机构id查询部门
	 * @param id
	 * @return
	 */
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id);
}
