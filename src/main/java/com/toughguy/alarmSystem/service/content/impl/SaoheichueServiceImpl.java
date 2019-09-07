package com.toughguy.alarmSystem.service.content.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.persist.content.prototype.IBaojingqingkuangDao;
import com.toughguy.alarmSystem.persist.content.prototype.ISaoheichueDao;
import com.toughguy.alarmSystem.service.content.prototype.ISaoheichueService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;


/**
 * 扫黑除恶Service实现类
 * @author zmk
 *
 */
@Service
public class SaoheichueServiceImpl extends GenericServiceImpl<Saoheichue, Integer> implements ISaoheichueService{

	@Override
	public Map<String, Saoheichue> findAllSH() {
		// TODO Auto-generated method stub
		Date date = new Date();
		Map<String ,String> map = new HashMap<String, String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String year =sf.format(date).substring(0,4);			//2019
		String month =sf.format(date).substring(5,7);			//09
		int time2 = Integer.parseInt(year);					   //转换为数字2019
		int month2 = Integer.parseInt(month)-1;				   //转换为数字08    0801-0901
		if(month=="01" || month.equals("01")) {		
			String stoptime=year+"-"+month+"-"+"01";					//2019-01-01
			time2=time2-1;
			month="12";
			String starttime=time2+"-"+month+"-"+"01";				//2018-12-01
			map.put("starttime", starttime);
			map.put("stoptime", stoptime);
		}else {
			String s="";
			if(month2<10) {
				s ="0"+month2;
			}else {
				s=month2+"";
			}
			String starttime=year+"-"+s+"-"+"01";				//2019-08-01
			String stoptime=year+"-"+month+"-"+"01";				//2019-09-01
			map.put("starttime", starttime);
			map.put("stoptime", stoptime);
		}
		Map<String,Saoheichue> shce = new HashMap<>();
		shce.put("shce", ((ISaoheichueDao)dao).findAllSH(map));
		return shce ;
	}

	@Override
	public Map<String, Saoheichue> findOneSH(String xzqh) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Map<String ,String> map = new HashMap<String, String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String year =sf.format(date).substring(0,4);			//2019
		String month =sf.format(date).substring(5,7);			//09
		String starttime=year+"-"+month+"-"+"01";				//2019-09-01
		int month2 = Integer.parseInt(month)+1;					//月+1
		int year2=Integer.parseInt(year)-1;						//年-1
		String s="";
		if(month2<10) {
			s ="0"+month2;
		}else {
			s=month2+"";
		}
		String stoptime=year+"-"+s+"-"+"01";
		map.put("starttime", starttime);
		map.put("stoptime", stoptime);
		map.put("xzqh", xzqh);
		Saoheichue saoheichue = ((ISaoheichueDao)dao).findOneSH(map);		//看当前月是否已经有数据了
		if(saoheichue==null) {		//截至当前还未提交
			if(month=="01" || month.equals("01")) {	
				String stopdate=year+"-"+month+"-"+"01";					//2019-01-01
				month="12";
				String startdate=year2+"-"+month+"-"+"01";					//2018-12-01    
				map.put("starttime", startdate);
				map.put("stoptime", stopdate);
				map.put("xzqh", xzqh);
				Map<String,Saoheichue> djshce = new HashMap<>();
				djshce.put("djshce",((ISaoheichueDao)dao).findOneSH(map) );
				return djshce ;
			}else {
				int parseInt = Integer.parseInt(month)-1;
				String ss="";
				if(parseInt<10) {
					ss="0"+parseInt;
				}else {
					ss=parseInt+"";
				}
				String startdate=year+"-"+ss+"-"+"01";					//2019-08-01
				String stopdate=year+"-"+month+"-"+"01";					//2019-09-01
				map.put("starttime", startdate);
				map.put("stoptime", stopdate);
				map.put("xzqh", xzqh);
				Map<String,Saoheichue> djshce = new HashMap<>();
				djshce.put("djshce", ((ISaoheichueDao)dao).findOneSH(map));
				return djshce ;
			}
		}else {
			Map<String,Saoheichue> djshce = new HashMap<>();
			djshce.put("djshce", saoheichue);
			return djshce ;
		}
	}

	@Override
	public Map<String, Object> selectList(String time, String xzqh) {		//time为统计月份的时间
		// TODO Auto-generated method stub
		if(time.equals("null") && xzqh.equals("全省")) {
			//什么都没选
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",time);
			List<Saoheichue> list = ((ISaoheichueDao)dao).selectAllList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> saoheichue = new HashMap<>();
			for(int i =0;i<list.size();i++) {
				Map<String,Object> aaa = new HashMap<>();
				String mc=list.get(i).getTjyf().substring(0, 4)+"年"+list.get(i).getTjyf().substring(5, 7)+"月扫黑除恶等专项行动有关警情线索统计月表";
				aaa.put("mc", mc);
				saoheichue.put(list.get(i).getTjyf().substring(0, 7), aaa);
			}
			return saoheichue;
		}else if(!time.equals("null") && xzqh.equals("全省")){
			//选了时间 没选地市
			String date = "%"+time+"%";
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",date);
			List<Saoheichue> list = ((ISaoheichueDao)dao).selectAllList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> saoheichue = new HashMap<>();
			for(int i = 0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				String mc=list.get(i).getTjyf().substring(0, 4)+"年"+list.get(i).getTjyf().substring(5, 7)+"月扫黑除恶等专项行动有关警情线索统计月表";
				aaa.put("mc", mc);
				saoheichue.put(list.get(i).getTjyf().substring(0, 7), aaa);
			}
			return saoheichue;
	
		}else if(!time.equals("null") && !xzqh.equals("全省")) {
			//选了时间  选了地市
			String date = "%"+time+"%";
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",date);
			List<Saoheichue> list = ((ISaoheichueDao)dao).selectList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> saoheichue = new HashMap<>();
			for(int i = 0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				String mc=list.get(i).getTjyf().substring(0, 4)+"年"+list.get(i).getTjyf().substring(5, 7)+"月"+list.get(i).getXzqh()+"扫黑除恶等专项行动有关警情线索统计月表";
				aaa.put("mc", mc);
				aaa.put("xzqh", list.get(i).getXzqh());
				aaa.put("tbr", list.get(i).getTbr());
				aaa.put("createTime", list.get(i).getTbdw());
				saoheichue.put(list.get(i).getTjyf().substring(0, 7), aaa);
			}
			return saoheichue;
		}else {
			//没选时间  选了地市			
			Map<String,String> map = new HashMap<>();
			map.put("xzqh", xzqh);
			map.put("time",time);
			List<Saoheichue> list = ((ISaoheichueDao)dao).selectList(map);
			if(list.size()<=0) {
				return null;
			}
			Map<String,Object> saoheichue = new HashMap<>();
			for(int i =0;i<list.size();i++) {
				Map<Object ,String> aaa = new HashMap<>();
				aaa.put("xzqh",list.get(i).getXzqh());
				aaa.put("tbr", list.get(i).getTbr());
				String mc=list.get(i).getTjyf().substring(0, 4)+"年"+list.get(i).getTjyf().substring(5, 7)+"月"+list.get(i).getXzqh()+"扫黑除恶等专项行动有关警情线索统计月表";
				aaa.put("mc",mc);
				aaa.put("createtime",list.get(i).getTbdw());
				saoheichue.put(list.get(i).getTjyf().substring(0, 7), aaa);
			}
			return saoheichue;
		}
		
	}

	@Override
	public List<Saoheichue> selectAll(String time) {
		// TODO Auto-generated method stub
		String date = "%"+time+"%";
		return ((ISaoheichueDao)dao).selectAll(date);
	}

	@Override
	public Saoheichue selectOne(String time,String xzqh) {
		// TODO Auto-generated method stub
		Map<String ,String> map = new HashMap<String, String>();
		String date = "%"+time+"%";
		map.put("time", date);
		map.put("xzqh", xzqh);
		return ((ISaoheichueDao)dao).selectOne(map);
	}

	@Override
	public List<Saoheichue> findOne(Map<String,String> map) {
		// TODO Auto-generated method stub
		return ((ISaoheichueDao)dao).findOne(map);
	}

	@Override
	public void updateAll(Saoheichue saoheichue) {
		// TODO Auto-generated method stub
		((ISaoheichueDao)dao).updateAll(saoheichue);
	}

	

}
