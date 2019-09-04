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
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.model.content.RecLHLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecBJFSTJBDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecBJFSTJBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 统计表表-报警方式统计表 Service实现类
 * @author zmk
 *
 */
@Service
public class RecBJFSTJBServiceImpl extends GenericServiceImpl<RecBJFSTJB, Integer> implements IRecBJFSTJBService{
	
	
	@Autowired
	IRecBJFSTJBDao  recBJFSTJBDao;
	
	@Override
	public List<RecBJFSTJB> findBJFSShen(String tjTime){
		// TODO Auto-generated method stub
		return recBJFSTJBDao.findBJFSShen(tjTime);
	}

	@Override
	public Map<String, Object> findBJFSSevenDayShen(String startTime, String endTime) {
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
		List<RecBJFSTJB> list = recBJFSTJBDao.findBJFSSevenDayShen(map);
		
		System.out.println(list);
		Set<String> set = new HashSet<>();
		set.add("电话报警");
		set.add("来人(来电)报警");
		set.add("技防报警");
		set.add("其它报警方式");
		set.add("短信报警");
		for(int i =0 ;i<list.size();i++) {
			set.add(list.get(i).getBjfsdm());
		}
		int total=0;
		Map<String,Object> sevenmap= new HashMap<>();
		Map<String,String> num= new HashMap<>();
		int z=0;
		for(String name:set) {
			List<RecBJFSTJB> arr = new ArrayList<>();
			for(int i =0;i<list.size();i++) {	
				if(list.get(i).getBjfsdm().equals(name) || list.get(i).getBjfsdm()==name) {		
					RecBJFSTJB bjfs= new RecBJFSTJB();
					bjfs.setBjfsdm(name);
					bjfs.setTjrq(list.get(i).getTjrq());
					bjfs.setJjsl(list.get(i).getJjsl());
					total=total+list.get(i).getJjsl();
					z=z+list.get(i).getJjsl();
					arr.add(bjfs);
				}
			}
			for(int a =0;a<7;a++) {
				if(arr.size()>0) {
					try {
						if(days.get(a)!= arr.get(a).getTjrq() && !days.get(a).equals(arr.get(a).getTjrq())) {		//
							RecBJFSTJB bjfs= new RecBJFSTJB();
							bjfs.setBjfsdm(name);
							bjfs.setTjrq(days.get(a));
							bjfs.setJjsl(0);
							arr.add(a,bjfs);
							a=0;
						}
					}catch (Exception e) {
						// TODO: handle exception
						RecBJFSTJB bjfs= new RecBJFSTJB();
						bjfs.setBjfsdm(name);
						bjfs.setTjrq(days.get(a));
						bjfs.setJjsl(0);
						arr.add(a,bjfs);
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
		return smap;
	
	}

	@Override
	public Map<String, Object> findSAlarmMode(String startTime, String endTime) {
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
		List<RecBJFSTJB> list = recBJFSTJBDao.findBJFSSevenDayShen(map);		//查询出时间区间的全省的警情数据
		List<RecBJFSTJB> listxzqh = recBJFSTJBDao.findSAlarmModeXZQH(map);		//查询出时间区间的全省的警情数据
		System.out.println(list);
		Set<String> set = new HashSet<>();
		set.add("电话报警");
		set.add("来人(来电)报警");
		set.add("技防报警");
		set.add("其它报警方式");
		set.add("短信报警");
		for(int i =0 ;i<list.size();i++) {
			set.add(list.get(i).getBjfsdm());
		}
		int total=0;
		Map<String,Object> sevenmap= new HashMap<>();
		Map<String,String> num= new HashMap<>();
		int z=0;
		for(String name:set) {
			List<RecBJFSTJB> arr = new ArrayList<>();
			for(int i =0;i<list.size();i++) {	
				if(list.get(i).getBjfsdm().equals(name) || list.get(i).getBjfsdm()==name) {		
					RecBJFSTJB bjfs= new RecBJFSTJB();
					bjfs.setBjfsdm(name);
					bjfs.setTjrq(list.get(i).getTjrq());
					bjfs.setJjsl(list.get(i).getJjsl());
					total=total+list.get(i).getJjsl();
					z=z+list.get(i).getJjsl();
					arr.add(bjfs);
				}
			}
			for(int a =0;a<7;a++) {
				if(arr.size()>0) {
					try {
						if(days.get(a)!= arr.get(a).getTjrq() && !days.get(a).equals(arr.get(a).getTjrq())) {		//
							RecBJFSTJB bjfs= new RecBJFSTJB();
							bjfs.setBjfsdm(name);
							bjfs.setTjrq(days.get(a));
							bjfs.setJjsl(0);
							arr.add(a,bjfs);
							a=0;
						}
					}catch (Exception e) {
						// TODO: handle exception
						RecBJFSTJB bjfs= new RecBJFSTJB();
						bjfs.setBjfsdm(name);
						bjfs.setTjrq(days.get(a));
						bjfs.setJjsl(0);
						arr.add(a,bjfs);
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
					if(listxzqh.get(i).getBjfsdm().equals(name) && listxzqh.get(i).getXzqhdm().equals(xzqh)) {
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
	public Map<String, Object> findCityAlarmMode(String startTime, String endTime, String xzqhdm) {
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
		List<RecBJFSTJB> list = recBJFSTJBDao.findCityAlarmMode(map);
		Set<String> set = new HashSet<>();
		set.add("电话报警");
		set.add("来人(来电)报警");
		set.add("投案自首");
		set.add("工作中发现");
		set.add("扭送现行");
		set.add("比对报警");
		set.add("上级交办");
		set.add("技防报警");
		set.add("短信报警");
		set.add("网络报警");
		set.add("其它部门移送");
		set.add("其它报警方式");
		int total = 0;
		for(int i =0 ;i<list.size();i++) {
			total=total+list.get(i).getJjsl();
			set.add(list.get(i).getBjfsdm());
		}
		Map<String,String> num = new HashMap<>();
		for(String name:set) {
			int z=0;
			List<RecBJFSTJB> arr= new ArrayList<>();
			for(int i =0;i<list.size();i++) {
				if(list.get(i).getBjfsdm()==name || list.get(i).getBjfsdm().equals(name)) {
					z=z+list.get(i).getJjsl();
					RecBJFSTJB bjfs= new RecBJFSTJB();
					bjfs.setBjfsdm(name);
					bjfs.setJjsl(list.get(i).getJjsl());
					bjfs.setTjrq(list.get(i).getTjrq());
					bjfs.setXzqhdm(list.get(i).getXzqhdm());
					arr.add(bjfs);
				}
			}
			for(int a =0;a<7;a++) {
				if(arr.size()>0) {
					try {
						if(days.get(a)!= arr.get(a).getTjrq() && !days.get(a).equals(arr.get(a).getTjrq())) {		//
							RecBJFSTJB bjfs= new RecBJFSTJB();
							bjfs.setBjfsdm(name);
							bjfs.setTjrq(days.get(a));
							bjfs.setJjsl(0);
							arr.add(a,bjfs);
							a=0;
						}
					}catch (Exception e) {
						// TODO: handle exception
						RecBJFSTJB bjfs= new RecBJFSTJB();
						bjfs.setBjfsdm(name);
						bjfs.setTjrq(days.get(a));
						bjfs.setJjsl(0);
						arr.add(a,bjfs);
						a=0;
					}
				}
			}

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
