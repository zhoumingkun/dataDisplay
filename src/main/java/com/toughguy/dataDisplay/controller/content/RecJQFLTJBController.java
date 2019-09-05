package com.toughguy.dataDisplay.controller.content;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQFLTJBService;

@RestController
@RequestMapping(value = "/recJQFLTJB")
public class RecJQFLTJBController {
	@Autowired
	private IRecJQFLTJBService recJQFLTJBService;
	/**
	 * 查询接警类型七天全省 （首页）
	 * @param tjTime 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLNum(String tjTime) {
		return  recJQFLTJBService.findJQFLNum(tjTime);
	}
	
	/**
	 * 查询接警类型七天全省总量 （首页）
	 * @param tjTime 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLNumHJ")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findJQFLNumHJ(String tjTime) {
		int jjslzs=recJQFLTJBService.findJQFLNumHJ(tjTime);
		List<RecJQFLTJB> list = recJQFLTJBService.findJQFLNum(tjTime);
		Map<String, Object> map = new HashMap<>();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();
		Set<String> set4 = new HashSet<>();
		Set<String> set5 = new HashSet<>();
		Set<String> set6 = new HashSet<>();
		Set<String> set7 = new HashSet<>();
		Set<String> set8 = new HashSet<>();
		Set<String> set9 = new HashSet<>();
		Set<String> set10 = new HashSet<>();
		for(int i =0 ;i<list.size();i++) {
			System.out.println("aaaaaa"+list.get(i).getFldmmc());
			if(list.get(i).getFldmmc()==null&&list.get(i).getFldmmc()==" "&& list.get(i).getFldmmc().equals("")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set10.add("其他警情");
				set10.add(jjsl);
				set10.add(zb);
			}else {
				
			if(list.get(i).getFldmmc().equals("交通类警情")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set1.add("交通类警情");
				set1.add(jjsl);
				set1.add(zb);
			}else if(list.get(i).getFldmmc().equals("行政(治安)警情")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set2.add("行政(治安)警情");
				set2.add(jjsl);
				set2.add(zb);
			}else if(list.get(i).getFldmmc().equals("刑事警情")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set3.add("刑事警情");
				set3.add(jjsl);
				set3.add(zb);
			}else if(list.get(i).getFldmmc().equals("纠纷")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set4.add("纠纷");
				set4.add(jjsl);
				set4.add(zb);
			}else if(list.get(i).getFldmmc().equals("群众求助")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set5.add("群众求助");
				set5.add(jjsl);
				set5.add(zb);
			}else if(list.get(i).getFldmmc().equals("消防救援")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set6.add("消防救援");
				set6.add(jjsl);
				set6.add(zb);
			}else if(list.get(i).getFldmmc().equals("应急联动事件")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set7.add("应急联动事件");
				set7.add(jjsl);
				set7.add(zb);
			}else if(list.get(i).getFldmmc().equals("灾害事故")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set8.add("灾害事故");
				set8.add(jjsl);
				set8.add(zb);
			}else if(list.get(i).getFldmmc().equals("群体事件")){
				DecimalFormat df = new DecimalFormat("0.0000");
				DecimalFormat dft = new DecimalFormat("0.00");
				int one = list.get(i).getJjsl();
				String format = df.format((float) one/jjslzs);
				Double aa = Double.parseDouble(format);
				String zb=dft.format(aa*100)+"";
				String jjsl=one+"";
				set9.add("群体事件");
				set9.add(jjsl);
				set9.add(zb);
			}
			}
			
		}
		map.put("交通类警情", set1);
		map.put("行政(治安)警情", set2);
		map.put("刑事警情", set3);
		map.put("纠纷", set4);
		map.put("群众求助", set5);
		map.put("消防救援", set6);
		map.put("应急联动事件", set7);
		map.put("灾害事故", set8);
		map.put("群体事件", set9);
		map.put("其他警情", set10);
		return map;
		
		
	}
	
	/**
	 * 查询各第二类的警情分类数量（首页今日、昨日、前日）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLsecondNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLsecondNum(String tjTime) {
		return  recJQFLTJBService.findJQFLsecondNum(tjTime);
	}
	
	/**
	 * 查询各大类的警情分类数量（二级页面各行政区划今日、昨日、前日）
	 * @param tjTime xzqhdm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLNumXZQHHJ")
//	@RequiresPermissions("dictXZQHB:getById")
	public int findJQFLNumXZQHHJ(String tjTime,String xzqhdm) {
		return  recJQFLTJBService.findJQFLNumXZQHHJ(tjTime,xzqhdm);
	}
	
	/**
	 * 查询各大类的警情分类数量总量（二级页面各行政区划今日、昨日、前日）
	 * @param tjTime xzqhdm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLNumXZQH")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLNumXZQH(String tjTime,String xzqhdm) {
		System.out.println(tjTime+"lll"+xzqhdm);
		return  recJQFLTJBService.findJQFLNumXZQH(tjTime,xzqhdm);
	}
	
	/**
	 * 
	 * 查询各第二类的警情分类数量（二级页面各行政区划今日、昨日、前日）
	 * @param tjTime xzqhdm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQFLsecondNumXZQH")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQFLTJB> findJQFLsecondNumXZQH(String tjTime,String xzqhdm) {
		System.out.println("测试测试"+recJQFLTJBService.findJQFLsecondNumXZQH(tjTime,xzqhdm));
		return  recJQFLTJBService.findJQFLsecondNumXZQH(tjTime,xzqhdm);
	}

}
