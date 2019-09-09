package com.toughguy.alarmSystem.controller.content;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.service.content.prototype.IDelayedService;

@RestController
@RequestMapping("/delayed")
public class DelayedController {
	
	@Autowired
	private IDelayedService delayedService;
	
	/**
	 * 查询添加延迟时间(省厅延迟的时候才会调用此接口)
	 * @return
	 */
	@RequestMapping("/selectDelayed")
//	@RequiresPermissions("delayed:selectDelayed")
	public String selectDelayed(Delayed delayed) {
		return delayedService.selectDelayed(delayed);
	}

	/**
	 * 查询现在地级市是否处于不能编辑状态
	 * @return
	 */
	@RequestMapping("/selectOne")
	public Map<String,String> selectOne(Delayed delayed) {
		System.out.println("进来了"+delayed);
		return delayedService.selectOne(delayed);
	}
	
	
	/**
	 * 省厅查询所有地级市的延迟情况
	 * @return
	 */
	@RequestMapping("/selectAll")
//	@RequiresPermissions("delayed:selectAll")
	public Map<String,Object> selectAll() {
		return delayedService.selectAll();
	}
	
}
