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
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecLHLXTJBService;

@RestController
@RequestMapping(value = "/recLHLXTJB")
public class RecLHLXTJBController {
	@Autowired
	private IRecLHLXTJBService recLHLXTJBService;
	/**
	 * 查询今日来话类型 （首页）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findLHLXShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecLHLXTJB> findLHLXShen(String tjTime){
		return  recLHLXTJBService.findLHLXShen(tjTime);
	}
	/**
	 * 查询来话类型七天全省 （首页）
	 * @param startTime endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findLHLXSevenDayShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findLHLXSevenDayShen(String startTime,String endTime){
		return  recLHLXTJBService.findLHLXSevenDayShen(startTime,endTime);
	}
	
	/**
	 * 查询省厅来话类型全部数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findSIncomingType")
	public Map<String,Object> findSIncomingType(String startTime,String endTime){
		return  recLHLXTJBService.findSIncomingType(startTime,endTime);
	}

	
	/**
	 * 查询地级市来话类型全部数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findCityIncomingType")
	public Map<String,Object> findCityIncomingType(String startTime,String endTime,String xzqhdm){
		return  recLHLXTJBService.findCityIncomingType(startTime,endTime,xzqhdm);
	}

}
