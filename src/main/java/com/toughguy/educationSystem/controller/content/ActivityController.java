package com.toughguy.educationSystem.controller.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.model.content.Activity;
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IActivityService;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
	@Autowired
	private IActivityService activityService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("activity:save")
	public String saveActivity(Activity activity) {
		try {
			activityService.save(activity);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("activity:edit")
	public String editActivity(Activity activity) {
		try {
			activityService.update(activity);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("activity:detele")
	public String deleteActivity(int id) {
		try {
			activityService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("activity:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Activity> pg = activityService.findPaginated(map);
			
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", pg.getData());
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	/**
	 * 这个只用于小程序文档访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getById")
//	@RequiresPermissions("activity:getById")
	public Activity getById(int id) {
		Activity activity =  activityService.find(id);
		activity.setHits(activity.getHits() + 1);
		return activity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("activity:findAll")
	public List<Activity> findAll() {
		return activityService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/findNew")
	//@RequiresPermissions("activity:findNew")
	public Activity findNew() {
		return activityService.findNew();
	}
	
	@ResponseBody
	@RequestMapping(value = "/findByTitle")
	//@RequiresPermissions("activity:findByTitle")
	public List<Activity> findByTitle(String title){
		return activityService.findByTitle(title);
	}
	
}
