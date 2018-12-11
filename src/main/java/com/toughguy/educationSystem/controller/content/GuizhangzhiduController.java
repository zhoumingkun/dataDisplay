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
import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IGuizhangzhiduService;

@Controller
@RequestMapping(value = "/guizhangzhidu")
public class GuizhangzhiduController {
	@Autowired
	private IGuizhangzhiduService guizhangzhiduService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("guizhangzhidu:save")
	public String saveGuizhangzhidu(Guizhangzhidu guizhangzhidu) {
		try {
			guizhangzhiduService.save(guizhangzhidu);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("guizhangzhidu:edit")
	public String editGuizhangzhidu(Guizhangzhidu guizhangzhidu) {
		try {
			guizhangzhiduService.update(guizhangzhidu);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("guizhangzhidu:detele")
	public String deleteGuizhangzhidu(int id) {
		try {
			guizhangzhiduService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("guizhangzhidu:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Guizhangzhidu> pg = guizhangzhiduService.findPaginated(map);
			
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
	
	
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("guizhangzhidu:findAll")
	public List<Guizhangzhidu> findAll() {
		return guizhangzhiduService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/findByTitle")
	//@RequiresPermissions("activity:findByTitle")
	public List<Guizhangzhidu> findByTitle(String title){
		return guizhangzhiduService.findByTitle(title);
	}
}
