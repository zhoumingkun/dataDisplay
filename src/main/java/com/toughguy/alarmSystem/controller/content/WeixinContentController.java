package com.toughguy.alarmSystem.controller.content;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.toughguy.alarmSystem.model.content.Zhengcefagui;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.security.CustomLoginToken;
import com.toughguy.alarmSystem.service.content.prototype.IZhengcefaguiService;
import com.toughguy.alarmSystem.util.DateUtil;

@Controller
@RequestMapping(value = "/weixinContent")
public class WeixinContentController {

    @Autowired
	private IZhengcefaguiService zhengcefaguiService;
	
	

	@ResponseBody
	@RequestMapping(value = "/dataZhengce")
	//@RequiresPermissions("zhengcefagui:data")
	public String dataZhengce(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Zhengcefagui> pg = zhengcefaguiService.findPaginated(map);
			
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
	 * 小程序政策法规文档访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getZhengce")
//	@RequiresPermissions("zhengcefagui:getById")
	public Zhengcefagui getZhengce(int id) {
		Zhengcefagui zhengcefagui =  zhengcefaguiService.find(id);
		zhengcefagui.setHits(zhengcefagui.getHits() + 1);
		zhengcefaguiService.update(zhengcefagui);
		return zhengcefagui;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
