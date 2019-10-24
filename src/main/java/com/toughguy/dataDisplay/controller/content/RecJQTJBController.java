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
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQTJBService;

@RestController
@RequestMapping(value = "/recJQTJB")
public class RecJQTJBController {
	@Autowired
	private IRecJQTJBService recJQTJBService;
	
	
	@ResponseBody
	@RequestMapping(value = "/findJQNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findJQNum(String tjTime) {
		System.out.println(recJQTJBService.findJQNum(tjTime));
		return  recJQTJBService.findJQNum(tjTime);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findJQSevenDayShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findJQSevenDayShen(String startTime,String endTime) {
		return  recJQTJBService.findJQSevenDayShen(startTime,endTime);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findXZQHNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findXZQHNum(String tjTime) {
		return  recJQTJBService.findXZQHNum(tjTime);
	}
	

}
