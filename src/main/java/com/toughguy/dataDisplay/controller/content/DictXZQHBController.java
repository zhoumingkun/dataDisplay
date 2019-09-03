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
import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IDictXZQHBService;

@RestController
@RequestMapping(value = "/dictXZQHB")
public class DictXZQHBController {
	@Autowired
	private IDictXZQHBService dictXZQHBService;
	
	/**
	 * 查询全部行政区划全部数据
	 * @return
	 */
	@RequestMapping(value = "/findAll")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<DictXZQHB> getAll() {
		return  dictXZQHBService.findAll();
	}
	
	@RequestMapping("/alterJJSLSX")
	public String save(List<DictXZQHB> list) {
		return  dictXZQHBService.save(list);
	}

	@RequestMapping("/findMapProportion")
	public Map<String,Object> findMapProportion(){
		return  dictXZQHBService.findMapProportion();
	}
}
