package com.toughguy.alarmSystem.persist.content.prototype;

import java.util.List;

import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.persist.prototype.IGenericDao;

public interface IDelayedDao extends IGenericDao<Delayed, Integer> {
	/**
	 * 查询该地市该月延迟情况
	 * @param delayed
	 * @return
	 */
	public Delayed findOne(Delayed delayed);

	/**
	 * 省厅查询所有地级市的延迟情况
	 * @param delayed
	 * @return
	 */
	public List<Delayed> selectAll(String time);

}
