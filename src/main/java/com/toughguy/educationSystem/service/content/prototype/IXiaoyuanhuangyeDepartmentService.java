package com.toughguy.educationSystem.service.content.prototype;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 校园黄页Service层接口类
 * @author zmk
 *
 */
public interface IXiaoyuanhuangyeDepartmentService extends IGenericService<XiaoyuanhuangyeDepartment, Integer>{
	/**
	 * 根据机构id查询部门
	 * @param id
	 * @return
	 */
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id);
}
