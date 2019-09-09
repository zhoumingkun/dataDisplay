package com.toughguy.alarmSystem.service.content.prototype;



import java.util.Map;

import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.service.prototype.IGenericService;

public interface IDelayedService extends IGenericService<Delayed, Integer> {
	/**
	 * 查询添加延迟时间
	 * @param delayed
	 * @return
	 */
	public String selectDelayed(Delayed delayed);
	
	/**
	 * 查询地级市是否处于不可编辑状态
	 * @param delayed
	 * @return
	 */
	public Map<String,String> selectOne(Delayed delayed);
	
	/**
	 * 省厅查询所有地级市的延迟情况
	 * @param delayed
	 * @return
	 */
	public Map<String,Object> selectAll();

}
