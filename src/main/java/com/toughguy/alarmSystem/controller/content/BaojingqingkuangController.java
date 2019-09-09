package com.toughguy.alarmSystem.controller.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.service.content.prototype.IBaojingqingkuangService;

@RestController
@RequestMapping(value = "/baojingqingkuang")
public class BaojingqingkuangController {
	@Autowired
	private IBaojingqingkuangService baojingqingkuangService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("baojingqingkuang:save")
	public String saveBaojingqingkuang(@RequestBody List<Baojingqingkuang> list) {
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
			if(list.get(0).getTjyf().equals(time2+"-"+month)) {
				map.put("time", time2+"-"+month);
				map.put("xzqh", list.get(0).getXzqh());
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
			if(list.get(0).getTjyf().equals(time+"-"+s)) {
				map.put("time",time+"-"+s);
				map.put("xzqh", list.get(0).getXzqh());
			}else {
				return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
			}
		}
		
		List<Baojingqingkuang> one = baojingqingkuangService.findOne(map);			//查询是否存在这个月的记录
		if(one.size()<=0) {												//没有数据添加
			try{
				if(list.size()>0) {
					for(int i=0;i<list.size();i++) {
						list.get(i).setState("1");
						baojingqingkuangService.save(list.get(i));
					}
					return "{ \"success\" : true }";
				}else {
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}catch(Exception e) {
				e.printStackTrace();
				return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
			}
		}else {
			try{
				if(list.size()>0) {
					for(int i=0;i<list.size();i++) {
						list.get(i).setId(one.get(i).getId());
						baojingqingkuangService.updateAll(list.get(i));
					}
					return "{ \"success\" : true }";
				}else {
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}catch(Exception e) {
				e.printStackTrace();
				return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
			}
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
	

	
	
	/**
	 * 省厅报警情况柱状图
	 * @return
	 */
	@RequestMapping("/findAllBJ")
	//@RequiresPermissions("baojingqingkuang:findAllBJ")
	public Map<String ,Baojingqingkuang> findAllBJ() {
		return baojingqingkuangService.findAllBJ();
	}
	
	
	
	/**
	 * 地级市报警情况柱状图
	 * @return
	 */
	@RequestMapping("/findOneBJ")
	//@RequiresPermissions("baojingqingkuang:findOneBJ")
	public Map<String ,Baojingqingkuang> findOneBJ(String xzqh) {
		return baojingqingkuangService.findOneBJ(xzqh);
	}
	
	
	
	/**
	 * 省厅查询报警情况统计表
	 * @return
	 * 前端需要传递查询月时间区间
	 */
	@RequestMapping("/selectAll")
	//@RequiresPermissions("baojingqingkuang:selectAll")
	public List<Baojingqingkuang> selectAll(String time) {
		return baojingqingkuangService.selectAll(time);
	}
	
	/**
	 * 地级市查询报警情况统计表
	 * @return
	 * 前端需要传递查询月时间区间和行政规划
	 */
	@RequestMapping("/selectOne")
	//@RequiresPermissions("baojingqingkuang:selectOne")
	public List<Baojingqingkuang> selectOne(String time,String xzqh) {
		System.out.println("-------------------"+baojingqingkuangService.selectOne(time,xzqh));
		return baojingqingkuangService.selectOne(time,xzqh);
	}
	
	
	
	/**
	 * 查询报警情况列表展示
	 * 传递时间为某年某月
	 * @return
	 */
	@RequestMapping("/selectList")
	//@RequiresPermissions("baojingqingkuang:selectList")
	public Map<String,Object> selectList(String time,String xzqh) {
		return baojingqingkuangService.selectList(time,xzqh);
	}
	
	
	//导出省报警情况
	@ResponseBody	
	@RequestMapping(value = "/exportShenBaojing")
//	@RequiresPermissions("exportShenBaojing:export")
	public String ExportShenBaojingqingkuang(HttpServletResponse response,String tjyf) {
		try {
			baojingqingkuangService.ExportShenBjqk(response, tjyf);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	//导出市级报警情况
	@ResponseBody	
	@RequestMapping(value = "/exportShiBaojing")
//	@RequiresPermissions("exportShenBaojing:export")
	public String ExportShiBaojingqingkuang(HttpServletResponse response,String tjyf,String xzqh) {
		try {
			baojingqingkuangService.ExportShiBjqk(response, tjyf, xzqh);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
		
	
}
