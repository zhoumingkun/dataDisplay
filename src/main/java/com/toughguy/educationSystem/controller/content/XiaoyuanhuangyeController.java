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
import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeService;

@Controller
@RequestMapping(value = "/xiaoyuanhuangye")
public class XiaoyuanhuangyeController {
	@Autowired
	private IXiaoyuanhuangyeService xiaoyuanhuangyeService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("xiaoyuanhuangye:save")
	public String saveXiaoyuanhuangye(Xiaoyuanhuangye xiaoyuanhuangye) {
		try {
			xiaoyuanhuangyeService.save(xiaoyuanhuangye);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("xiaoyuanhuangye:edit")
	public String editXiaoyuanhuangye(Xiaoyuanhuangye xiaoyuanhuangye) {
		try {
			xiaoyuanhuangyeService.update(xiaoyuanhuangye);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("xiaoyuanhuangye:detele")
	public String deleteXiaoyuanhuangye(int id) {
		try {
			xiaoyuanhuangyeService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("xiaoyuanhuangye:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Xiaoyuanhuangye> pg = xiaoyuanhuangyeService.findPaginated(map);
			
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
	//@RequiresPermissions("xiaoyuanhuangye:findAll")
	public List<Xiaoyuanhuangye> findAll() {
		return xiaoyuanhuangyeService.findAll();
	}
	
	/**
	 * 根据部门名称查询
	 * */
	@ResponseBody
	@RequestMapping(value = "/findBySectionName")
	// @RequiresPermissions("xiaoyuanhuangye:findBySectionName")
	public List<Xiaoyuanhuangye> findBySectionName(String sectionName) {
		return xiaoyuanhuangyeService.findBySectionName(sectionName);
	}
	
	/**
	 * 根据类型查询部门名称
	 * */
	@ResponseBody
	@RequestMapping(value = "/findSectionNameByType")
	// @RequiresPermissions("xiaoyuanhuangye:findSectionNameByType")
	public Xiaoyuanhuangye findSectionNameByType(int type)  {
		return xiaoyuanhuangyeService.findSectionNameByType(type);
	}
	
}
