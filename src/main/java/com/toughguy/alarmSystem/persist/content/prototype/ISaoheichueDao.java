package com.toughguy.alarmSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.alarmSystem.model.content.Zhengcefagui;
import com.toughguy.alarmSystem.persist.prototype.IGenericDao;

/**
 * 政策法规Dao接口类
 * @author zmk
 *
 */
public interface IZhengcefaguiDao extends IGenericDao<Zhengcefagui, Integer>{
	/**
 	 * 根据标题查询
 	 * */
 	public List<Zhengcefagui> findByTitle(String titie);
	

}
