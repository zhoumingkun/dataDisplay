package com.toughguy.alarmSystem.controller.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.service.content.prototype.IBaojingqingkuangService;

@Controller
@RequestMapping(value = "/baojingqingkuang")
public class BaojingqingkuangController {
	@Autowired
	private IBaojingqingkuangService baojingqingkuangService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	@RequiresPermissions("baojingqingkuang:save")
	public String saveBaojingqingkuang(Baojingqingkuang baojingqingkuang) {
		try {
			baojingqingkuangService.save(baojingqingkuang);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	@RequiresPermissions("baojingqingkuang:edit")
	public String editBaojingqingkuang(Baojingqingkuang baojingqingkuang) {
		try {
			baojingqingkuangService.update(baojingqingkuang);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	@RequiresPermissions("baojingqingkuang:delete")
	public String deleteBaojingqingkuang(int id) {
		try {
			baojingqingkuangService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("baojingqingkuang:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Baojingqingkuang> pg = baojingqingkuangService.findPaginated(map);
			
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
	@RequestMapping(value = "/getById")
//	@RequiresPermissions("baojingqingkuang:getById")
	public Baojingqingkuang getById(int id) {
		return  baojingqingkuangService.find(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("baojingqingkuang:findAll")
	public List<Baojingqingkuang> findAll() {
		return baojingqingkuangService.findAll();
	}
	
	
	
}
