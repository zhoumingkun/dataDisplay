package com.toughguy.dataDisplay.controller.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecBJFSTJBService;

@RestController
@RequestMapping(value = "/recBJFSTJB")
public class RecBJFSTJBController {
	@Autowired
	private IRecBJFSTJBService recBJFSTJBService;
	/**
	 * 查询今日报警方式 （首页）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findBJFSShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecBJFSTJB> findBJFSShen(String tjTime){
		return  recBJFSTJBService.findBJFSShen(tjTime);
	}
	/**
	 * 查询报警方式七天全省 （首页）
	 * @param startTime endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findBJFSSevenDayShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findBJFSSevenDayShen(String startTime,String endTime){
		return  recBJFSTJBService.findBJFSSevenDayShen(startTime,endTime);
	}
	/**
	 * 查询省报警方式数据分析
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findSAlarmMode")
	public Map<String,Object> findSAlarmMode(String startTime,String endTime){
		return  recBJFSTJBService.findSAlarmMode(startTime,endTime);
	}
	
	/**
	 * 查询地级市报警方式数据分析
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findCityAlarmMode")
	public Map<String,Object> findCityAlarmMode(String startTime,String endTime,String xzqhdm){
		return  recBJFSTJBService.findCityAlarmMode(startTime,endTime,xzqhdm);
	}


}
