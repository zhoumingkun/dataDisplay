package com.toughguy.dataDisplay.controller.content;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.toughguy.dataDisplay.model.content.RecJQFLTJB;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQFLTJBService;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQTJBService;

@RestController
@RequestMapping(value = "/recJQTJB")
public class RecJQTJBController {
	@Autowired
	private IRecJQTJBService recJQTJBService;
	
	@Autowired
	private IRecJQFLTJBService recJQFLTJBService;
	
	/**
	 * 查询警情统计监测（今日首页）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findJQNum(String tjTime) {
		System.out.println(recJQTJBService.findJQNum(tjTime));
		return  recJQTJBService.findJQNum(tjTime);
	}
	/**
	 * 查询近七天警情统计（首页）
	 * @param startTime endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQSevenDayShen")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findJQSevenDayShen(String startTime,String endTime) {
		return  recJQTJBService.findJQSevenDayShen(startTime,endTime);
	}
	/**
	 * 查询各行政区划警情数量（当日地图）
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findXZQHNum")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findXZQHNum(String tjTime) {
		return  recJQTJBService.findXZQHNum(tjTime);
	}
	
	/**
	 * 查询各行政区划警情数量环比
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findXZQHNumHB")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findXZQHNumHB(String tjTime,String xzqhdm,String qtTime) {
		Map<String,Object> map = new HashMap<>();
		Map<String,String> fmap = new HashMap<>();
		List<RecJQTJB> arr = recJQTJBService.findXZQHNumHB(tjTime,xzqhdm);
		Float f=0.0f;
		Float ff=0.0f;
		for(RecJQTJB d:arr){
			f += d.getHb();
			ff+=d.getYxhb();
		}
		fmap.put("报警总数环比", f+"");
		fmap.put("有效警情环比", ff+"");
		
		List<RecJQTJB> findJQNum = recJQTJBService.findJQNumEveryXZQH(tjTime,xzqhdm);
		for(int i=0;i<findJQNum.size();i++) {
			fmap.put("昨日报警总数", findJQNum.get(i).getJjsl()+"");
			fmap.put("昨日有效警情", findJQNum.get(i).getYxjq()+"");	
		}
		List<RecJQTJB> findJQNum2 = recJQTJBService.findJQNumEveryXZQH(qtTime,xzqhdm);
		for(int i=0;i<findJQNum2.size();i++) {
			fmap.put("前日报警总数", findJQNum2.get(i).getJjsl()+"");
			fmap.put("前日有效警情", findJQNum2.get(i).getYxjq()+"");	
		}
		map.put("Sjqhbfx", fmap);
		
		List<RecJQFLTJB> findJQFLNum = recJQFLTJBService.findJQFLWDLXZQH(tjTime,xzqhdm);			//昨日
		Map<String,String> aa= new HashMap<>();
		Map<String,String> cc= new HashMap<>();
		for(int i=0;i<findJQFLNum.size();i++) {
			if(findJQFLNum.get(i).getFldmmc()=="诈骗" || findJQFLNum.get(i).getFldmmc().equals("诈骗")) {
				aa.put("诈骗", findJQFLNum.get(i).getJjsl()+"");
				cc.put("诈骗", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="盗窃" || findJQFLNum.get(i).getFldmmc().equals("盗窃")) {
				aa.put("盗窃", findJQFLNum.get(i).getJjsl()+"");	
				cc.put("盗窃", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="贩毒" || findJQFLNum.get(i).getFldmmc().equals("贩毒")) {
				aa.put("贩毒", findJQFLNum.get(i).getJjsl()+"");
				cc.put("贩毒", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="强奸" || findJQFLNum.get(i).getFldmmc().equals("强奸")) {
				aa.put("强奸", findJQFLNum.get(i).getJjsl()+"");
				cc.put("强奸", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="抢劫" || findJQFLNum.get(i).getFldmmc().equals("抢劫")) {
				aa.put("抢劫", findJQFLNum.get(i).getJjsl()+"");
				cc.put("抢劫", findJQFLNum.get(i).getHb()+"");
			}
		}
		map.put("yesterday", aa);
		map.put("yesterdayHB", cc);
		List<RecJQFLTJB> findJQFLNum2 = recJQFLTJBService.findJQFLWDLXZQH(qtTime,xzqhdm);			//前日
		Map<String,String> bb= new HashMap<>();
		for(int i=0;i<findJQFLNum2.size();i++) {
			if(findJQFLNum2.get(i).getFldmmc()=="诈骗" || findJQFLNum2.get(i).getFldmmc().equals("诈骗")) {
				bb.put("诈骗", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="盗窃" || findJQFLNum2.get(i).getFldmmc().equals("盗窃")) {
				bb.put("盗窃", findJQFLNum2.get(i).getJjsl()+"");	
			}
			if(findJQFLNum2.get(i).getFldmmc()=="贩毒" || findJQFLNum2.get(i).getFldmmc().equals("贩毒")) {
				bb.put("贩毒", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="强奸" || findJQFLNum2.get(i).getFldmmc().equals("强奸")) {
				bb.put("强奸", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="抢劫" || findJQFLNum2.get(i).getFldmmc().equals("抢劫")) {
				bb.put("抢劫", findJQFLNum2.get(i).getJjsl()+"");
			}
		}
		map.put("beforeday", bb);
		map.put("beforeday", bb);
		//Map<String,String> dd = new HashMap<>();
		List<String> dd = new ArrayList<String>();
		dd.add("诈骗");
		dd.add("盗窃");
		dd.add("贩毒");
		dd.add("强奸");
		dd.add("抢劫");
		map.put("aa",dd);
		return map;
	}
	
	/**
	 * 查询警情数量环比
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findNumHB")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findNumHB(String tjTime,String qtTime) {		//传递昨天和前天的时间
		Map<String,Object> map = new HashMap<>();
		Map<String,String> fmap = new HashMap<>();
		List <RecJQTJB> arr=recJQTJBService.findNumHB(tjTime);
//		Float f=0.0f;
//		Float ff=0.0f;
//		for(RecJQTJB d:arr){
//			f += d.getHb();
//			ff+=d.getYxhb();
//		}
//		fmap.put("报警总数环比", f+"");
//		fmap.put("有效警情环比", ff+"");
		
		List<RecJQTJB> findJQNum = recJQTJBService.findJQNum(tjTime);
		for(int i=0;i<findJQNum.size();i++) {
			fmap.put("昨日报警总数", findJQNum.get(i).getJjsl()+"");
			fmap.put("昨日有效警情", findJQNum.get(i).getYxjq()+"");	
		}
		List<RecJQTJB> findJQNum2 = recJQTJBService.findJQNum(qtTime);
		for(int i=0;i<findJQNum2.size();i++) {
			fmap.put("前日报警总数", findJQNum2.get(i).getJjsl()+"");
			fmap.put("前日有效警情", findJQNum2.get(i).getYxjq()+"");	
		}
		String zrbj=fmap.get("昨日报警总数");
		int zrbjsl=Integer.parseInt(zrbj);
		String qrbj=fmap.get("前日报警总数");
		int qrbjsl=Integer.parseInt(qrbj);
//		int bjhb=(zrbjsl-qrbjsl)/qrbjsl;
		DecimalFormat df = new DecimalFormat("0.00");
		String num = df.format((float) (zrbjsl-qrbjsl)/qrbjsl);
		double d = Double.valueOf(num);
		fmap.put("报警总数环比", d+"");
		
		
		String zryx=fmap.get("昨日有效警情");
		int zryxsl=Integer.parseInt(zryx);
		String qryx=fmap.get("前日有效警情");
		int qryxsl=Integer.parseInt(qryx);
		DecimalFormat df1 = new DecimalFormat("0.00");
		String num1 = df1.format((float) (zryxsl-qryxsl)/qryxsl);
		double f = Double.valueOf(num1);
		fmap.put("报警总数环比", f+"");
		
		map.put("Sjqhbfx", fmap);
		List<RecJQFLTJB> findJQFLNum = recJQFLTJBService.findJQFLWDL(tjTime);			//昨日
		Map<String,String> aa= new HashMap<>();
		Map<String,String> cc= new HashMap<>();
		for(int i=0;i<findJQFLNum.size();i++) {
			if(findJQFLNum.get(i).getFldmmc()=="诈骗" || findJQFLNum.get(i).getFldmmc().equals("诈骗")) {
				aa.put("诈骗", findJQFLNum.get(i).getJjsl()+"");
				cc.put("诈骗", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="盗窃" || findJQFLNum.get(i).getFldmmc().equals("盗窃")) {
				aa.put("盗窃", findJQFLNum.get(i).getJjsl()+"");	
				cc.put("盗窃", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="贩毒" || findJQFLNum.get(i).getFldmmc().equals("贩毒")) {
				aa.put("贩毒", findJQFLNum.get(i).getJjsl()+"");
				cc.put("贩毒", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="强奸" || findJQFLNum.get(i).getFldmmc().equals("强奸")) {
				aa.put("强奸", findJQFLNum.get(i).getJjsl()+"");
				cc.put("强奸", findJQFLNum.get(i).getHb()+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="抢劫" || findJQFLNum.get(i).getFldmmc().equals("抢劫")) {
				aa.put("抢劫", findJQFLNum.get(i).getJjsl()+"");
				cc.put("抢劫", findJQFLNum.get(i).getHb()+"");
			}
		}
		map.put("yesterday", aa);
		map.put("yesterdayHB", cc);
		
		List<RecJQFLTJB> findJQFLNum2 = recJQFLTJBService.findJQFLWDL(qtTime);			//前日
		Map<String,String> bb= new HashMap<>();
		for(int i=0;i<findJQFLNum2.size();i++) {
			if(findJQFLNum2.get(i).getFldmmc()=="诈骗" || findJQFLNum2.get(i).getFldmmc().equals("诈骗")) {
				bb.put("诈骗", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="盗窃" || findJQFLNum2.get(i).getFldmmc().equals("盗窃")) {
				bb.put("盗窃", findJQFLNum2.get(i).getJjsl()+"");	
			}
			if(findJQFLNum2.get(i).getFldmmc()=="贩毒" || findJQFLNum2.get(i).getFldmmc().equals("贩毒")) {
				bb.put("贩毒", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="强奸" || findJQFLNum2.get(i).getFldmmc().equals("强奸")) {
				bb.put("强奸", findJQFLNum2.get(i).getJjsl()+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="抢劫" || findJQFLNum2.get(i).getFldmmc().equals("抢劫")) {
				bb.put("抢劫", findJQFLNum2.get(i).getJjsl()+"");
			}
		}
		map.put("beforeday", bb);
		List<String> dd = new ArrayList<String>();
		dd.add("诈骗");
		dd.add("盗窃");
		dd.add("贩毒");
		dd.add("强奸");
		dd.add("抢劫");
		map.put("aa",dd);
		return map;
	}
	
	/**
	 * 查询有效警情数量环比(首页)
	 * @param tjTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findNumSYHB")
//	@RequiresPermissions("dictXZQHB:getById")
	public Map<String,Object> findNumSYHB(String tjTime,String qtTime) {		//传递昨天和前天的时间
//		List <RecJQTJB> arr=recJQTJBService.findNumHB(tjTime);
//		int i = 0;
//		Float f=0.0f;
//		for(RecJQTJB d:arr){
//			f += d.getYxhb();
//		}
//		System.out.println("哈哈哈"+f);
//		return f;
//	}
		Map<String,Object> map = new HashMap<>();
		Map<String,String> fmap = new HashMap<>();
		List <RecJQTJB> arr=recJQTJBService.findNumHB(tjTime);
//		Float f=0.0f;
//		Float ff=0.0f;
//		for(RecJQTJB d:arr){
//			f += d.getHb();
//			ff+=d.getYxhb();
//		}
//		fmap.put("报警总数环比", f+"");
//		fmap.put("有效警情环比", ff+"");
		
		List<RecJQTJB> findJQNum = recJQTJBService.findJQNum(tjTime);
		for(int i=0;i<findJQNum.size();i++) {
			fmap.put("昨日报警总数", findJQNum.get(i).getJjsl()+"");
			fmap.put("昨日有效警情", findJQNum.get(i).getYxjq()+"");	
		}
		List<RecJQTJB> findJQNum2 = recJQTJBService.findJQNum(qtTime);
		for(int i=0;i<findJQNum2.size();i++) {
			fmap.put("前日报警总数", findJQNum2.get(i).getJjsl()+"");
			fmap.put("前日有效警情", findJQNum2.get(i).getYxjq()+"");	
		}
		String zrbj=fmap.get("昨日报警总数");
		int zrbjsl=Integer.parseInt(zrbj);
		String qrbj=fmap.get("前日报警总数");
		int qrbjsl=Integer.parseInt(qrbj);
//		int bjhb=(zrbjsl-qrbjsl)/qrbjsl;
		DecimalFormat df = new DecimalFormat("0.00");
		String num = df.format((float) (zrbjsl-qrbjsl)/qrbjsl);
		double d = Double.valueOf(num);
		fmap.put("报警总数环比", d+"");
		
		
		String zryx=fmap.get("昨日有效警情");
		int zryxsl=Integer.parseInt(zryx);
		String qryx=fmap.get("前日有效警情");
		int qryxsl=Integer.parseInt(qryx);
		DecimalFormat df1 = new DecimalFormat("0.00");
		String num1 = df1.format((float) (zryxsl-qryxsl)/qryxsl);
		double f = Double.valueOf(num1);
		fmap.put("报警总数环比", f+"");
		
		map.put("Sjqhbfx", fmap);
		return map;
	}


}
