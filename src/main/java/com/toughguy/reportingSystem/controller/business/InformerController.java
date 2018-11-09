package com.toughguy.reportingSystem.controller.business;

import java.util.HashMap;
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
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;

@Controller
@RequestMapping(value = "/informer")
public class InformerController {
	@Autowired
	private IInformerService informerService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("informer:save")
	public String saveinformer(Informer informer) {
		try {
			informerService.save(informer);
			System.out.println(informer);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/get")
	public Informer getInformer(String openId) {
		System.out.println(openId);
		Informer inf=informerService.getInformer(openId);
		if(inf != null){
			return inf;
		}else{
			return null;
		}
	}

	
	@ResponseBody
	@RequestMapping(value = "/edit")
//	@RequiresPermissions("informer:edit")
	public String editinformer(Informer informer) {
		try {
			informerService.update(informer);
			System.out.println(informer);
			System.out.println("改完了");
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
//	@RequiresPermissions("informer:detele")
	public String deleteinformer(int id) {
		try {
			informerService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
//	@RequiresPermissions("informer:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Informer> pg = informerService.findPaginated(map);
			
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
}
