package com.toughguy.dataDisplay.service.content.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.toughguy.dataDisplay.model.content.RecBJFSTJB;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecLHLXTJBDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecLHLXTJBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 统计表表-来话类型统计表  Service实现类
 * @author zmk
 *
 */
@Service
public class RecLHLXTJBServiceImpl extends GenericServiceImpl<RecLHLXTJB, Integer> implements IRecLHLXTJBService{
	
	
	@Autowired
	IRecLHLXTJBDao  recLHLXTJBDao;
	
	@Override
	public List<RecLHLXTJB> findAll(){
		// TODO Auto-generated method stub
		return recLHLXTJBDao.findAll();
	}
	@Override
	public List<RecLHLXTJB> findLHLXShen(String tjTime){
		// TODO Auto-generated method stub
		return recLHLXTJBDao.findLHLXShen(tjTime);
	}

	@Override
	public List<RecLHLXTJB> findLHLXSevenDayShen(String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map<String ,String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return recLHLXTJBDao.findLHLXSevenDayShen(map);
	}
	
	@Override
	public Map<String, Object> findSIncomingType(String startTime, String endTime) {
		// TODO Auto-generated method stub
        List<String> days = new ArrayList<String>();						//获取时间区间的全部日期
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		Map<String,Object> smap= new HashMap<>();
		Map<String,String> map = new HashMap<>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<RecLHLXTJB> list = recLHLXTJBDao.findLHLXSevenDayShen(map);		//查询出时间区间的全省的来话类型数据
		List<RecLHLXTJB> listxzqh = recLHLXTJBDao.findSIncomingTypeXZQH(map);		//查询出时间区间的全省的来话类型数据(含行政区划)
		System.out.println(list);
		Set<String> set = new HashSet<>();
		set.add("报警求助、举报投诉");
		set.add("处警反馈");
		set.add("信息咨询");
		set.add("重复报警");
		set.add("骚扰电话");
		set.add("系统测试");
		set.add("其它处理类型");
		for(int i =0 ;i<list.size();i++) {
			set.add(list.get(i).getLhlxdm());
		}
		int total=0;
		Map<String,Object> sevenmap= new HashMap<>();
		Map<String,String> num= new HashMap<>();
		int z=0;
		for(String name:set) {
			List<RecLHLXTJB> arr = new ArrayList<>();
			for(int i =0;i<list.size();i++) {	
				if(list.get(i).getLhlxdm().equals(name) || list.get(i).getLhlxdm()==name) {		
					RecLHLXTJB lhlx= new RecLHLXTJB();
					lhlx.setLhlxdm(name);
					lhlx.setTjrq(list.get(i).getTjrq());
					lhlx.setJjsl(list.get(i).getJjsl());
					total=total+list.get(i).getJjsl();
					z=z+list.get(i).getJjsl();
					arr.add(lhlx);
				}
			}			
			for(int a =0;a<7;a++) {
				if(arr.size()>0) {
					try {
						if(days.get(a)!= arr.get(a).getTjrq() && !days.get(a).equals(arr.get(a).getTjrq())) {		//
							RecLHLXTJB lhlx= new RecLHLXTJB();
							lhlx.setLhlxdm(name);
							lhlx.setTjrq(days.get(a));
							lhlx.setJjsl(0);
							arr.add(a,lhlx);
							a=0;
						}
					}catch (Exception e) {
						// TODO: handle exception
						RecLHLXTJB lhlx= new RecLHLXTJB();
						lhlx.setLhlxdm(name);
						lhlx.setTjrq(days.get(a));
						lhlx.setJjsl(0);
						arr.add(a,lhlx);
						a=0;
					}
				}
			}
			num.put(name,z+"");
			z=0;
			sevenmap.put(name, arr);
		}
		smap.put("sevenDays", sevenmap);
		System.out.println("-----------------"+num);
		Map<String,String> proportion = new HashMap<>();
		DecimalFormat df = new DecimalFormat("0.00000");
		DecimalFormat dft = new DecimalFormat("0.00");
		for(String name :set) {
			if(num.get(name)==null || num.get(name).equals("null")) {
				proportion.put(name, "0");
			}else {
				int one = Integer.parseInt(num.get(name));
				String format = df.format((float) one/total);
				Double aa = Double.parseDouble(format);
				proportion.put(name, dft.format(aa*100)+"");
			}
		}
		smap.put("proportion", proportion);

		Set<String> set2= new HashSet<>();
		for(int i=0;i<listxzqh.size();i++) {
			set2.add(listxzqh.get(i).getXzqhdm());
		}
		for(String name:set) {
			Map<String,String> xzqhnumber = new HashMap<>();
			for(String xzqh:set2) {
				int h=0;
				for(int i=0;i<listxzqh.size();i++) {
					if(listxzqh.get(i).getLhlxdm().equals(name) && listxzqh.get(i).getXzqhdm().equals(xzqh)) {
						h=h+listxzqh.get(i).getJjsl();
					}
				}
				xzqhnumber.put(xzqh+"市", h+"");
			}
			smap.put(name, xzqhnumber);
		}
		return smap;
	}
	
	
	@Override
	public Map<String, Object> findCityIncomingType(String startTime, String endTime, String xzqhdm) {
		// TODO Auto-generated method stub
        List<String> days = new ArrayList<String>();						//获取时间区间的全部日期
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		
		Map<String,String> map = new HashMap<>();
		Map<String,Object> cmap = new HashMap<>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("xzqhdm", xzqhdm);
		List<RecLHLXTJB> list = recLHLXTJBDao.findCityIncomingType(map);
		Set<String> set = new HashSet<>();
		set.add("报警求助、举报投诉");
		set.add("处警反馈");
		set.add("信息咨询");
		set.add("重复报警");
		set.add("骚扰电话");
		set.add("系统测试");
		set.add("其它处理类型");
		int total = 0;
		for(int i =0 ;i<list.size();i++) {
			total=total+list.get(i).getJjsl();
			set.add(list.get(i).getLhlxdm());
		}
		Map<String,String> num = new HashMap<>();
		for(String name:set) {
			int z=0;
			List<RecLHLXTJB> arr= new ArrayList<>();
			for(int i =0;i<list.size();i++) {
				if(list.get(i).getLhlxdm()==name || list.get(i).getLhlxdm().equals(name)) {
					z=z+list.get(i).getJjsl();
					RecLHLXTJB lhlx= new RecLHLXTJB();
					lhlx.setLhlxdm(name);
					lhlx.setJjsl(list.get(i).getJjsl());
					lhlx.setTjrq(list.get(i).getTjrq());
					lhlx.setXzqhdm(list.get(i).getXzqhdm());
					arr.add(lhlx);
				}
			}
			for(int a =0;a<7;a++) {
				if(arr.size()>0) {
					try {
						if(days.get(a)!= arr.get(a).getTjrq() && !days.get(a).equals(arr.get(a).getTjrq())) {		//
							RecLHLXTJB lhlx= new RecLHLXTJB();
							lhlx.setLhlxdm(name);
							lhlx.setTjrq(days.get(a));
							lhlx.setJjsl(0);
							arr.add(a,lhlx);
							a=0;
						}
					}catch (Exception e) {
						// TODO: handle exception
						RecLHLXTJB lhlx= new RecLHLXTJB();
						lhlx.setLhlxdm(name);
						lhlx.setTjrq(days.get(a));
						lhlx.setJjsl(0);
						arr.add(a,lhlx);
						a=0;
					}
					
				}
				
			}
			System.out.println("-------------------"+arr);
			num.put(name, z+"");
			cmap.put(name, arr);
		}
		Map<String,String> proportion = new HashMap<>();
		DecimalFormat df = new DecimalFormat("0.0000");
		DecimalFormat dft = new DecimalFormat("0.00");
		for(String name:set) {
			if(num.get(name)==null || num.get(name).equals("null")) {
				proportion.put(name, "0");
			}else {
				int one = Integer.parseInt(num.get(name));
				String format = df.format((float) one/total);
				Double aa = Double.parseDouble(format);
				proportion.put(name, dft.format(aa*100)+"");
			}
		}
		cmap.put("proportion", proportion);
		return cmap;
	}

		            
				            

}
