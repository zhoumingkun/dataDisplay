package com.toughguy.educationSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

/**
 * 规章制度Dao接口类
 * @author zmk
 *
 */
public interface IGuizhangzhiduDao extends IGenericDao<Guizhangzhidu, Integer>{
	/**
 	 * 根据标题查询
 	 * */
 	public List<Guizhangzhidu> findByTitle(String titie);
	

}
