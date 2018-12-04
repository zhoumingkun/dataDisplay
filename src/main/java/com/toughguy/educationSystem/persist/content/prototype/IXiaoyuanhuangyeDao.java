package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 校园黄页Dao接口类
 * @author zmk
 *
 */
public interface IXiaoyuanhuangyeDao extends IGenericDao<Xiaoyuanhuangye, Integer>{
	/**
	 * 根据部门名称查询
	 * */
	public List<Xiaoyuanhuangye>findBySectionName(String sectionName);
	

}
