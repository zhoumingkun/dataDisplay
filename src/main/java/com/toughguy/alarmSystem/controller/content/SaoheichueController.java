package com.toughguy.alarmSystem.controller.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
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
import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.service.content.prototype.ISaoheichueService;

@RestController
@RequestMapping(value = "/saoheichue")
public class SaoheichueController {
	@Autowired
	private ISaoheichueService saoheichueService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("saoheichue:save")
	public String saveSaoheichue( Saoheichue saoheichue) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sf.format(date).substring(0,4);		//截取年
			String month = sf.format(date).substring(5,7);		//截取月份
			int time2 = Integer.parseInt(time);					//转换为数字2019
			int month2 = Integer.parseInt(month)-1;				//转换为数字09
			Map<String,String> map = new HashMap<>();
			if(month=="01" || month.equals("01")) {				//如果是一月份填报时间就应该是去年的12月份
				month="12";
				time2=time2-1;
				if(saoheichue.getTjyf().equals(time2+"-"+month)) {
					map.put("time", time2+"-"+month);
					map.put("xzqh", saoheichue.getXzqh());
				}else {
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}else {
				String s =null;
				if(month2<10) {
					s="0"+month2;
				}else {
					s=month2+"";
				}
				if(saoheichue.getTjyf().equals(time+"-"+s)) {
					map.put("time",time+"-"+s);
					map.put("xzqh", saoheichue.getXzqh());
				}else {
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}
			List<Saoheichue> one = saoheichueService.findOne(map);			//查询是否存在这个月的记录
			if(one.size()<=0) {												//没有数据添加
				try{
					saoheichue.setState("1");
					saoheichueService.save(saoheichue);
					return "{ \"success\" : true }";
				}catch(Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}else {
				try{
					saoheichue.setId(one.get(0).getId());
					saoheichueService.updateAll(saoheichue);
					return "{ \"success\" : true }";
				}catch(Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	@RequiresPermissions("saoheichue:edit")
	public String editSaoheichue(Saoheichue saoheichue) {
		try {
			saoheichueService.update(saoheichue);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	@RequiresPermissions("saoheichue:delete")
	public String deleteSaoheichue(int id) {
		try {
			saoheichueService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("saoheichue:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Saoheichue> pg = saoheichueService.findPaginated(map);
			
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
//	@RequiresPermissions("zhengcefagui:getById")
	public Saoheichue getById(int id) {
		return  saoheichueService.find(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("zhengcefagui:findAll")
	public List<Saoheichue> findAll() {
		return saoheichueService.findAll();
	}
	
	
	/**
	 * 省厅扫黑除恶柱状图
	 * @return
	 */
	@RequestMapping("/findAllSH")
	//@RequiresPermissions("zhengcefagui:findAllSH")
	public Map<String ,Saoheichue> findAllBJ() {
		return saoheichueService.findAllSH();
	}
	
	/**
	 * 地级市扫黑除恶柱状图
	 * @return
	 */
	@RequestMapping("/findOneSH")
	//@RequiresPermissions("zhengcefagui:findOneSH")
	public Map<String ,Saoheichue> findOneBJ(String xzqh) {
		return saoheichueService.findOneSH(xzqh);
	}
	
	/**
	 * 省厅扫黑除恶表数据
	 * @return
	 */
	@RequestMapping("/selectAll")
	//@RequiresPermissions("zhengcefagui:selectAll")
	public List<Saoheichue> selectAll(String time) {
		return saoheichueService.selectAll(time);
	}
	
	/**
	 * 地级市扫黑除表数据
	 * @return
	 */
	@RequestMapping("/selectOne")
	//@RequiresPermissions("zhengcefagui:selectOne")
	public Saoheichue selectOne(String time,String xzqh) {
		return saoheichueService.selectOne(time,xzqh);
	}
	
	
	/**
	 * 省厅扫黑除恶统计列表
	 * @return
	 */
	@RequestMapping("/selectList")
	//@RequiresPermissions("zhengcefagui:selectList")
	public Map<String,Object> selectList(String time,String xzqh) {
		return saoheichueService.selectList(time,xzqh);
	}
	

}
