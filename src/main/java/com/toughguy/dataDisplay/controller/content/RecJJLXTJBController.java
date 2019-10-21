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
	
	
	

	
	
	@ResponseBody
	@RequestMapping(value = "/getAll")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJJLXTJB> getAll() {
		return  recJJLXTJBService.findAll();
	}
	

}
