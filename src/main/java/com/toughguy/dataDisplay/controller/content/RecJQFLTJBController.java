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
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQFLTJBService;

@RestController
@RequestMapping(value = "/recJQFLTJB")
public class RecJQFLTJBController {
	@Autowired
	private IRecJQFLTJBService recJQFLTJBService;
	/**
	 * 查询接警类型七天全省 （首页）
	 * @param tjTime 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLNum(String tjTime) {
		return  recJQFLTJBService.findJQFLNum(tjTime);
	}
	
	/**
	 * 查询各第二类的警情分类数量（首页今日、昨日、前日）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLsecondNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLsecondNum(String tjTime) {
		return  recJQFLTJBService.findJQFLsecondNum(tjTime);
	}
	

}
