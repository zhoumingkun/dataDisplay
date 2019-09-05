package com.toughguy.alarmSystem.service.content.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.persist.content.prototype.IBaojingqingkuangDao;
import com.toughguy.alarmSystem.service.content.prototype.IBaojingqingkuangService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;


/**
 * 报警情况Service实现类
 * @author zmk
 *
 */
@Service
public class BaojingqingkuangServiceImpl extends GenericServiceImpl<Baojingqingkuang, Integer> implements IBaojingqingkuangService{

	@Override
	public Map<String ,Baojingqingkuang> findAllBJ() {
		// TODO Auto-generated method stub
		Date date = new Date();
		Map<String ,String> map = new HashMap<String, String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String year =sf.format(date).substring(0,4);			//2019
		String month =sf.format(date).substring(5,7);			//09
		String starttime=year+"-"+month+"-"+"01";				//2019-09-01
		int stop = Integer.parseInt(month)+1;					//2019-10-01
		String stoptime=year+"-"+stop+"-"+"01";
		map.put("starttime", starttime);
		map.put("stoptime", stoptime);
		Map<String,Baojingqingkuang> bjqk = new HashMap<>();
		bjqk.put("bjqk", ((IBaojingqingkuangDao)dao).findAllBJ(map));
		return bjqk ;
	}


	@Override
	public Map<String ,Baojingqingkuang> findOneBJ(String xzqh) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Map<String ,String> map = new HashMap<String, String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String year =sf.format(date).substring(0,4);			//2019
		String month =sf.format(date).substring(5,7);			//09
		String starttime=year+"-"+month+"-"+"01";				//2019-09-01
		int stop = Integer.parseInt(month)+1;					//2019-10-01 
		String stoptime=year+"-"+stop+"-"+"01";
		map.put("starttime", starttime);
		map.put("stoptime", stoptime);
		map.put("xzqh", xzqh);
		Map<String,Baojingqingkuang> djbjqk = new HashMap<>();
		djbjqk.put("djbjqk", ((IBaojingqingkuangDao)dao).findOneBJ(map));
		return djbjqk ;
	}


	@Override
	public Map<String, String> insertAll(List<Baojingqingkuang> list) {
		// TODO Auto-generated method stub
		try {
			for(int i =0;i<list.size(); i++) {
				Baojingqingkuang baojing = list.get(i);
				((IBaojingqingkuangDao)dao).save(baojing);
			}
			Map<String,String> map = new HashMap<>();
			map.put("200", "添加成功");
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			Map<String,String> map = new HashMap<>();
			map.put("400", "添加失败");
			return map;
		}
	}


	@Override
	public List<Baojingqingkuang> selectAll(String starttime,String stoptime) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<>();
		map.put("starttime", starttime);
		map.put("stoptime", stoptime);
		return ((IBaojingqingkuangDao)dao).selectAll(map);
	}


	@Override
	public List<Baojingqingkuang> selectOne(String starttime,String stoptime, String xzqh) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<>();
		map.put("starttime", starttime);
		map.put("stoptime", stoptime);
		map.put("xzqh", xzqh);
		return  ((IBaojingqingkuangDao)dao).selectOne(map);
	}


	@Override
	public  Map<String,Object> selectList(String time, String xzqh) {
		
		// TODO Auto-generated method stub
		if(time.equals("null") && xzqh.equals("全省")) {
			//什么都没选
			System.out.println("1");
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",time);
			List<Baojingqingkuang> list = ((IBaojingqingkuangDao)dao).selectAllList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> baojingList = new HashMap<>();
			for(int i =0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				String mc=list.get(i).getBjqk().substring(0, 4)+"年"+list.get(i).getBjqk().substring(5, 7)+"月全省报警情况统计月表";
				aaa.put("mc", mc);
				baojingList.put(list.get(i).getBjqk().substring(0, 7), aaa);
			}
			return baojingList;	
		}else if(!time.equals("null") && xzqh.equals("全省")){
			//选了时间 没选地市
			System.out.println("2");
			String date = "%"+time.substring(0, 7)+"%";
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",date);
			List<Baojingqingkuang> list = ((IBaojingqingkuangDao)dao).selectAllList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> baojingList = new HashMap<>();
			for(int i = 0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				String mc=list.get(i).getBjqk().substring(0, 4)+"年"+list.get(i).getBjqk().substring(5, 7)+"月全省报警情况统计月表";
				aaa.put("mc", mc);
				baojingList.put(list.get(i).getBjqk().substring(0, 7), aaa);
			}
			return baojingList;
	
		}else if(!time.equals("null") && !xzqh.equals("全省")) {
			//选了时间  选了地市
			String date = "%"+time.substring(0, 7)+"%";
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",date);
			List<Baojingqingkuang> list = ((IBaojingqingkuangDao)dao).selectList(map);
			if(list.size()<=0) {
				return null;
			}
			Set<String> set = new HashSet<>();
			Map<String,Object> baojingList = new HashMap<>();
			for(int i =0; i<list.size();i++) {
				set.add(list.get(i).getBjqk().substring(5,7));		//唯一月份
			}
			baojingList.put("tbr",list.get(0).getTbr());
			baojingList.put("createTime", list.get(0).getBjqk());
			baojingList.put("xzqh", list.get(0).getXzqh());
			String mc=list.get(0).getBjqk().substring(0, 4)+"年"+list.get(0).getBjqk().substring(5, 7)+"月"+list.get(0).getXzqh()+"市报警情况统计月表";
			baojingList.put("mc",mc);
			return baojingList;
		}else {
			//没选时间  选了地市
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",time);
			List<Baojingqingkuang> list = ((IBaojingqingkuangDao)dao).selectList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> baojingList = new HashMap<>();
			for(int i =0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				aaa.put("xzqh",list.get(i).getXzqh());
				aaa.put("tbr", list.get(i).getTbr());
				String mc=list.get(i).getBjqk().substring(0, 4)+"年"+list.get(i).getBjqk().substring(5, 7)+"月"+list.get(i).getXzqh()+"市报警情况统计月表";
				aaa.put("mc",mc);
				aaa.put("createtime",list.get(i).getBjqk());
				baojingList.put(list.get(i).getBjqk().substring(0, 7), aaa);
			}
			return baojingList;
		}
		
		

	}

	

}
