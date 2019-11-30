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
		Map<String,String> zmap = new HashMap<>();
		Map<String,String> hmap = new HashMap<>();
//		List <RecJQTJB> arr=recJQTJBService.findNumHB(tjTime);
		
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
		DecimalFormat df = new DecimalFormat("0.000");
		String num = df.format((float) (zrbjsl-qrbjsl)/qrbjsl);
		double d = Double.valueOf(num);
		fmap.put("报警总数环比", d+"");
		
		String zryx=fmap.get("昨日有效警情");
		int zryxsl=Integer.parseInt(zryx);
		String qryx=fmap.get("前日有效警情");
		int qryxsl=Integer.parseInt(qryx);
		DecimalFormat df1 = new DecimalFormat("0.000");
		String num1 = df1.format((float) (zryxsl-qryxsl)/qryxsl);
		double f = Double.valueOf(num1);
		fmap.put("有效警情环比", f+"");
		
		map.put("Sjqhbfx", fmap);
		List<RecJQFLTJB> findJQFLNum = recJQFLTJBService.findJQFLWDL(tjTime);			//昨日
		Map<String,String> aa= new HashMap<>();//昨日数量
		Map<String,String> cc= new HashMap<>();
		int a=0;
		int b=0;
		int c=0;
		int c1=0;
		int e=0;
		List<String> list = new ArrayList<>();
		list.add("诈骗");
		list.add("盗窃");
		list.add("贩毒");
		list.add("强奸");
		list.add("抢劫");
		for(int i=0;i<findJQFLNum.size();i++) {
			if(findJQFLNum.get(i).getFldmmc()=="诈骗" || findJQFLNum.get(i).getFldmmc().equals("诈骗")) {
				a+=findJQFLNum.get(i).getJjsl();
				aa.put("诈骗", a+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="盗窃" || findJQFLNum.get(i).getFldmmc().equals("盗窃")) {
				b+=findJQFLNum.get(i).getJjsl();
				aa.put("盗窃", b+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="贩毒" || findJQFLNum.get(i).getFldmmc().equals("贩毒")) {
				c+=findJQFLNum.get(i).getJjsl();
				aa.put("贩毒", c+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="强奸" || findJQFLNum.get(i).getFldmmc().equals("强奸")) {
				c1+=findJQFLNum.get(i).getJjsl();
				aa.put("强奸", c1+"");
			}
			if(findJQFLNum.get(i).getFldmmc()=="抢劫" || findJQFLNum.get(i).getFldmmc().equals("抢劫")) {
				e+=findJQFLNum.get(i).getJjsl();
				aa.put("抢劫", e+"");
			}
		}
		for(int i =0;i<list.size();i++) {
			String string = aa.get(list.get(i));
			if(string==null) {
				aa.put(list.get(i), "0");
			}
			
		}
		zmap.put("ZTSL", aa+"");
		
		List<RecJQFLTJB> findJQFLNum2 = recJQFLTJBService.findJQFLWDL(qtTime);			//前日
		Map<String,String> bb= new HashMap<>();//前日数量
		int g=0;
		int h=0;
		int j=0;
		int k=0;
		int l=0;
		for(int i=0;i<findJQFLNum2.size();i++) {
			if(findJQFLNum2.get(i).getFldmmc()=="诈骗" || findJQFLNum2.get(i).getFldmmc().equals("诈骗")) {
				g+=findJQFLNum2.get(i).getJjsl();
				bb.put("诈骗", g+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="盗窃" || findJQFLNum2.get(i).getFldmmc().equals("盗窃")) {
				h+=findJQFLNum2.get(i).getJjsl();
				bb.put("盗窃", h+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="贩毒" || findJQFLNum2.get(i).getFldmmc().equals("贩毒")) {
				j+=findJQFLNum2.get(i).getJjsl();
				bb.put("贩毒", j+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="强奸" || findJQFLNum2.get(i).getFldmmc().equals("强奸")) {
				k+=findJQFLNum2.get(i).getJjsl();
				bb.put("强奸", k+"");
			}
			if(findJQFLNum2.get(i).getFldmmc()=="抢劫" || findJQFLNum2.get(i).getFldmmc().equals("抢劫")) {
				l+=findJQFLNum2.get(i).getJjsl();
				bb.put("抢劫", l+"");
			}
		}
		for(int i =0;i<list.size();i++) {
			String string = bb.get(list.get(i));
			if(string==null) {
				bb.put(list.get(i), "0");
			}
		}
		zmap.put("QTSL", bb+"");
		
		if(!aa.get("诈骗").equals("0")){
			if(!bb.get("诈骗").equals("0")){
				String zrzp=aa.get("诈骗");
				int zrzpsl=Integer.parseInt(zrzp);
				String qrzp=bb.get("诈骗");
				int qrzpsl=Integer.parseInt(qrzp);
				DecimalFormat df2 = new DecimalFormat("0.000");
				String num2 = df2.format((float) (zrzpsl-qrzpsl)/qrzpsl);
				double d2 = Double.valueOf(num2);
				hmap.put("诈骗总数环比", d2+"");
			}else{
				hmap.put("诈骗总数环比", "0");
			}
		}else{
			if(!bb.get("诈骗").equals("0")){
//				zmap.put("QTSL", bb+"");
				int zrzpsl=0;
				String qrzp=bb.get("诈骗");
				int qrzpsl=Integer.parseInt(qrzp);
				DecimalFormat df2 = new DecimalFormat("0.000");
				String num2 = df2.format((float) (zrzpsl-qrzpsl)/qrzpsl);
				double d2 = Double.valueOf(num2);
				hmap.put("诈骗总数环比", d2+"");
			}else{
				hmap.put("诈骗总数环比", "0");
			}
			
		}
		
		if(!aa.get("盗窃").equals("0")){
			if(!bb.get("盗窃").equals("0")){
				String zrdq=aa.get("盗窃");
				int zrdqsl=Integer.parseInt(zrdq);
				String qrdq=bb.get("盗窃");
				int qrdqsl=Integer.parseInt(qrdq);
				DecimalFormat df3 = new DecimalFormat("0.000");
				String num3 = df3.format((float) (zrdqsl-qrdqsl)/qrdqsl);
				double d3 = Double.valueOf(num3);
				hmap.put("盗窃总数环比", d3+"");
			}else{
				hmap.put("盗窃总数环比", "0");
			}
		}else{
			if(!bb.get("盗窃").equals("0")){
//				String zrdq=aa.get("盗窃");
				int zrdqsl=0;
				String qrdq=bb.get("盗窃");
				int qrdqsl=Integer.parseInt(qrdq);
				DecimalFormat df3 = new DecimalFormat("0.000");
				String num3 = df3.format((float) (zrdqsl-qrdqsl)/qrdqsl);
				double d3 = Double.valueOf(num3);
				hmap.put("盗窃总数环比", d3+"");
			}else{
				hmap.put("盗窃总数环比", "0");
			}
			
		}
		
//		String zrfd=aa.get("贩毒");
//		int zrfdsl=Integer.parseInt(zrfd);
//		String qrfd=bb.get("贩毒");
//		int qrfdsl=Integer.parseInt(qrfd);
//		DecimalFormat df4 = new DecimalFormat("0.000");
//		String num4 = df4.format((float) (zrfdsl-qrfdsl)/qrfdsl);
//		double d4 = Double.valueOf(num4);
//		fmap.put("贩毒总数环比", d4+"");
		
		if(!aa.get("强奸").equals("0")){
			if(!bb.get("强奸").equals("0")){
				String zrqj=aa.get("强奸");
				int zrqjsl=Integer.parseInt(zrqj);
				String qrqj=bb.get("强奸");
				int qrqjsl=Integer.parseInt(qrqj);
				DecimalFormat df5 = new DecimalFormat("0.000");
				String num5 = df5.format((float) (zrqjsl-qrqjsl)/qrqjsl);
				double d5 = Double.valueOf(num5);
				hmap.put("强奸总数环比", d5+"");
			}else{
				hmap.put("强奸总数环比", "0");
			}
		}else{
			if(!bb.get("强奸").equals("0")){
				int zrqjsl=0;
				String qrqj=bb.get("强奸");
				int qrqjsl=Integer.parseInt(qrqj);
				DecimalFormat df5 = new DecimalFormat("0.000");
				String num5 = df5.format((float) (zrqjsl-qrqjsl)/qrqjsl);
				double d5 = Double.valueOf(num5);
				hmap.put("强奸总数环比", d5+"");
			}else{
				hmap.put("强奸总数环比", "0");
			}
			
		}
		
		if(!aa.get("抢劫").equals("0")){
			if(!bb.get("抢劫").equals("0")){
				String zrqiangjie=aa.get("抢劫");
				String qrqiangjie=bb.get("抢劫");
				int zrqiangjiesl=Integer.parseInt(zrqiangjie);
				int qrqiangjiesl=Integer.parseInt(qrqiangjie);
				DecimalFormat df6 = new DecimalFormat("0.000");
				String num6 = df6.format((float) (zrqiangjiesl-qrqiangjiesl)/qrqiangjiesl);
				double d6 = Double.valueOf(num6);
				hmap.put("抢劫总数环比", d6+"");
			}else{
				hmap.put("抢劫总数环比", "0");
			}
		}else{
			if(!bb.get("抢劫").equals("0")){
				int zrqiangjiesl=0;
				String qrqiangjie=bb.get("抢劫");
				int qrqiangjiesl=Integer.parseInt(qrqiangjie);
				DecimalFormat df6 = new DecimalFormat("0.000");
				String num6 = df6.format((float) (zrqiangjiesl-qrqiangjiesl)/qrqiangjiesl);
				double d6 = Double.valueOf(num6);
				hmap.put("抢劫总数环比", d6+"");
			}else{
				hmap.put("抢劫总数环比", "0");
			}
			
		}

		map.put("wdlhb",hmap);
		map.put("wdlsl",zmap);
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
		Map<String,Object> map = new HashMap<>();
		Map<String,String> fmap = new HashMap<>();
		List <RecJQTJB> arr=recJQTJBService.findNumHB(tjTime);
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
		DecimalFormat df = new DecimalFormat("0.000");
		String num = df.format((float) (zrbjsl-qrbjsl)/qrbjsl);
		double d = Double.valueOf(num);
		fmap.put("报警总数环比", d+"");
		
		
		String zryx=fmap.get("昨日有效警情");
		int zryxsl=Integer.parseInt(zryx);
		String qryx=fmap.get("前日有效警情");
		int qryxsl=Integer.parseInt(qryx);
		DecimalFormat df1 = new DecimalFormat("0.000");
		String num1 = df1.format((float) (zryxsl-qryxsl)/qryxsl);
		double f = Double.valueOf(num1);
		fmap.put("有效警情环比", f+"");
		
		map.put("Sjqhbfx", fmap);
		return map;
	}
	
	/**
	 * 查询环比页面各行政区划警情检测数量
	 * @param startTime endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findJQNumEveryXZQH")
//	@RequiresPermissions("dictXZQHB:getById")
	public List<RecJQTJB> findJQNumEveryXZQH(String tjTime,String xzqhdm) {
		return  recJQTJBService.findJQNumEveryXZQH(tjTime,xzqhdm);
	}


}
