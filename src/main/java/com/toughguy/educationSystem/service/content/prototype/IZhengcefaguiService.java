package com.toughguy.educationSystem.service.content.prototype;
import java.util.List;

import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.model.content.Zhengcefagui;
import com.toughguy.educationSystem.service.prototype.IGenericService;

/**
 * 政策法规Service层接口类
 * @author zmk
 *
 */
public interface IZhengcefaguiService extends IGenericService<Zhengcefagui, Integer>{
	/**
 	 * 根据标题查询
 	 * */
 	public List<Zhengcefagui> findByTitle(String title);
}
