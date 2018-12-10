package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 规章制度Dao接口类
 * @author zmk
 *
 */
public interface ISizhengjiansheDao extends IGenericDao<Sizhengjianshe, Integer>{
	/**
 	 * 根据标题查询
 	 * */
 	public List<Sizhengjianshe> findByTitle(String titie);
 	/**
 	 * 根据标来源查询
 	 * */
 	public List<Sizhengjianshe> findBySource(String source);

}
