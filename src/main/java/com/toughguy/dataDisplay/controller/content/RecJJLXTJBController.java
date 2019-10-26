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
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecJJLXTJBService;

@RestController
@RequestMapping(value = "/recJJLXTJB")
public class RecJJLXTJBController {
	@Autowired
	private IRecJJLXTJBService recJJLXTJBService;
	/**
	 * 查询今日接警类型 （首页）
	 * @param tjrq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJJLXShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJJLXTJB> findJJLXShen(String tjrq){
		return  recJJLXTJBService.findJJLXShen(tjrq);
	}
	/**
	 * 查询接警类型七天全省 （首页）
	 * @param startTime endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJJLXSevenDayShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJJLXTJB> findJJLXSevenDayShen(String startTime,String endTime){
		return  recJJLXTJBService.findJJLXSevenDayShen(startTime,endTime);
	}
	
	/**
	 * 根据时间区间查询省的警情数据分析
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findSAlarmData")
	public Map<String,Object> findSAlarmData(String startTime,String endTime){
		return recJJLXTJBService.findSAlarmData(startTime,endTime);
	}

	/**
	 * 根据时间区间查询地级市的警情数据分析
	 * @param startTime
	 * @param endTime
	 * @param xzqhdm
	 * @return
	 */
	@RequestMapping("/findCityAlarmData")
	public Map<String,Object> findCityAlarmData(String startTime,String endTime,String xzqhdm){
		return recJJLXTJBService.findCityAlarmData(startTime,endTime,xzqhdm);
	}

}
