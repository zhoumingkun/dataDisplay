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
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.ISizhengjiansheService;

@Controller
@RequestMapping(value = "/sizhengjianshe")
public class SizhengjiansheController {
	@Autowired
	private ISizhengjiansheService sizhengjiansheService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("sizhengjianshe:save")
	public String saveSizhengjianshe(Sizhengjianshe sizhengjianshe) {
		try {
			sizhengjiansheService.save(sizhengjianshe);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("sizhengjianshe:edit")
	public String editSizhengjianshe(Sizhengjianshe sizhengjianshe) {
		try {
			sizhengjiansheService.update(sizhengjianshe);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("sizhengjianshe:detele")
	public String deleteSizhengjianshe(int id) {
		try {
			sizhengjiansheService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("content:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Sizhengjianshe> pg = sizhengjiansheService.findPaginated(map);
			
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
	//@RequiresPermissions("sizhengjianshe:findAll")
	public List<Sizhengjianshe> findAll() {
		return sizhengjiansheService.findAll();
	}
	
}
