package com.toughguy.educationSystem.service.content.prototype;
import java.util.List;

import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 校园黄页Service层接口类
 * @author zmk
 *
 */
public interface IXiaoyuanhuangyeService extends IGenericService<Xiaoyuanhuangye, Integer>{
	/**
	 * 根据部门名称查询
	 * */
	public List<Xiaoyuanhuangye>findBySectionName(String sectionName);
	/**
	 * 根据类型查询部门名称
	 * 
	 * */
	public Xiaoyuanhuangye findSectionNameByType(int type);

}
