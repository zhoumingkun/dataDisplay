package com.toughguy.alarmSystem.persist.content.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.toughguy.alarmSystem.model.content.Zhengcefagui;
import com.toughguy.alarmSystem.persist.content.prototype.IZhengcefaguiDao;
import com.toughguy.alarmSystem.persist.impl.GenericDaoImpl;
/**
 * 政策法规Dao实现类
 * @author zmk
 *
 */
@Repository
public class ZhengcefaguiImpl extends GenericDaoImpl<Zhengcefagui, Integer> implements IZhengcefaguiDao{
	/**
	 * 根据标题查询
	 * 
	 * */
	@Override
 	public List<Zhengcefagui> findByTitle(String title) {
 		// TODO Auto-generated method stub
 		return sqlSessionTemplate.selectList(typeNameSpace + ".findByTitle", title);
 	}

}
