package com.toughguy.alarmSystem.controller.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.persist.content.prototype.IDelayedDao;
import com.toughguy.alarmSystem.service.content.prototype.ISaoheichueService;

@RestController
@RequestMapping(value = "/saoheichue")
public class SaoheichueController {
	@Autowired
	private ISaoheichueService saoheichueService;
	
	@Autowired
	private IDelayedDao delayedDao;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("saoheichue:save")
	public String saveSaoheichue( Saoheichue saoheichue) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Delayed dela= new Delayed();
			dela.setXzqh(saoheichue.getXzqh());
			String time3 = sf.format(date);		//09   日
			String time1 = sf.format(date).substring(0, 7);		//2019-09
			dela.setDateStart(time1+"-01");
			Delayed two = delayedDao.findOne(dela);
			String ss = time3.substring(8);
			if(two==null && Integer.parseInt(ss)>10){
				return "{ \"success\" : false, \"msg\" : \"该操作已过期，请联系管理员\" }";
			}else if(two !=null && Integer.parseInt(two.getDelayedStop().substring(8))<Integer.parseInt(time3.substring(8)) && two.getState().equals("-1")) {
				return "{ \"success\" : false, \"msg\" : \"该操作已过期\" }";
			}
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
					return "{ \"success\" : false, \"msg\" : \"只可添加上月数据\" }";
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
					return "{ \"success\" : false, \"msg\" : \"只可添加上月数据\" }";
				}
			}
			List<Saoheichue> one = saoheichueService.findOne(map);			//查询是否存在这个月的记录
			if(one.size()<=0) {												//没有数据添加
				try{
					if(two!=null) {
						saoheichue.setState("-1");
					}else {
						saoheichue.setState("1");
					}
					saoheichue.setHj(saoheichue.getShcedzxs()+saoheichue.getDjqbfzxs()+saoheichue.getDjwwfzxs()+saoheichue.getPhstfzxs()+saoheichue.getFfjzfzxs()+saoheichue.getDxwlfzxs());
					saoheichueService.save(saoheichue);
					return "{ \"success\" : true }";
				}catch(Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
				}
			}else {
				try{
					saoheichue.setId(one.get(0).getId());
					saoheichue.setHj(saoheichue.getShcedzxs()+saoheichue.getDjqbfzxs()+saoheichue.getDjwwfzxs()+saoheichue.getPhstfzxs()+saoheichue.getFfjzfzxs()+saoheichue.getDxwlfzxs());
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
//	@RequiresPermissions("saoheichue:edit")
	public String editSaoheichue(@RequestBody List<Saoheichue> saoheichue) {
		try {
			for(int i=0;i<saoheichue.size();i++) {
				saoheichueService.updateAllShen(saoheichue.get(i));
			}
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
//	@RequiresPermissions("saoheichue:getById")
	public Saoheichue getById(int id) {
		return  saoheichueService.find(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("saoheichue:findAll")
	public List<Saoheichue> findAll() {
		return saoheichueService.findAll();
	}
	
	
	/**
	 * 省厅扫黑除恶柱状图
	 * @return
	 */
	@RequestMapping("/findAllSH")
	//@RequiresPermissions("saoheichue:findAllSH")
	public Map<String ,Saoheichue> findAllBJ() {
		return saoheichueService.findAllSH();
	}
	
	/**
	 * 地级市扫黑除恶柱状图
	 * @return
	 */
	@RequestMapping("/findOneSH")
	//@RequiresPermissions("saoheichue:findOneSH")
	public Map<String ,Saoheichue> findOneBJ(String xzqh) {
		return saoheichueService.findOneSH(xzqh);
	}
	
	/**
	 * 省厅扫黑除恶表数据
	 * @return
	 */
	@RequestMapping("/selectAll")
	//@RequiresPermissions("saoheichue:selectAll")
	public Map<String,Object> selectAll(String xzqh,String time) {
		return saoheichueService.selectAll(time);
	}
	
	/**
	 * 地级市扫黑除表数据
	 * @return
	 */
	@RequestMapping("/selectOne")
	//@RequiresPermissions("saoheichue:selectOne")
	public Saoheichue selectOne(String time,String xzqh) {
		return saoheichueService.selectOne(time,xzqh);
	}
	
	
	/**
	 * 省厅扫黑除恶统计列表
	 * @return
	 */
	@RequestMapping("/selectList")
	//@RequiresPermissions("saoheichue:selectList")
	public Map<String,Object> selectList(String time,String xzqh) {
		return saoheichueService.selectList(time,xzqh);
	}
	
	//导出省报警情况
		@ResponseBody	
		@RequestMapping(value = "/exportShenSaohei")
//		@RequiresPermissions("saoheichue:exportShenBaojing")
		public String ExportShenSaoheichue(HttpServletResponse response,String tjyf) {
			try {
				saoheichueService.ExportShenShce(response, tjyf);
				return "{ \"success\" : true }";
			} catch (Exception e) {
				e.printStackTrace();
				return "{ \"success\" : false }";
			}
		}
	//导出市报警情况
		@ResponseBody	
		@RequestMapping(value = "/exportShiSaohei")
//		@RequiresPermissions("saoheichue:exportShenBaojing")
		public String ExportShiSaoheichue(HttpServletResponse response,String tjyf,String xzqh) {
			try {
				saoheichueService.ExportShiShce(response, tjyf,xzqh);
				return "{ \"success\" : true }";
			} catch (Exception e) {
				e.printStackTrace();
				return "{ \"success\" : false }";
			}
		}
}
