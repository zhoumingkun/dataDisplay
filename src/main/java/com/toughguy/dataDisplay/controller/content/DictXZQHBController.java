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
import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.persist.content.prototype.IDictXZQHBDao;
import com.toughguy.dataDisplay.service.content.prototype.IDictXZQHBService;

@RestController
@RequestMapping(value = "/dictXZQHB")
public class DictXZQHBController {
	@Autowired
	private IDictXZQHBService dictXZQHBService;
	
	@Autowired
	private IDictXZQHBDao  dictXZQHBDao;
	
	/**
	 * 查询全部行政区划全部数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findAll")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<DictXZQHB> getAll() {
		return  dictXZQHBService.findAll();
	}
	
	@ResponseBody
	@RequestMapping("/alterJJSLSX")
	public String save(List<DictXZQHB> list) {
		return  dictXZQHBService.save(list);
	}

	@ResponseBody
	@RequestMapping("/findMapProportion")
	public Map<String,Object> findMapProportion(){
		return  dictXZQHBService.findMapProportion();
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateXZQH")
	public String updateXZQH( @RequestBody  List<DictXZQHB> list){	
		System.out.println("我行政区划");
//		return recGradeService.alterGrade(list);
		try{
			for(int i =0;i<list.size();i++) {
				dictXZQHBDao.updateXZQH(list.get(i));
			}
			return "{ \"success\" : true }";
		}catch (Exception e) {
			// TODO: handle exception
			return "{ \"success\" : false }";
		}
	}
}
