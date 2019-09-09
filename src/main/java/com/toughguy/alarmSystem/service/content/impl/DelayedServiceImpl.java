package com.toughguy.alarmSystem.service.content.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.toughguy.alarmSystem.model.content.Delayed;
import com.toughguy.alarmSystem.persist.content.prototype.IDelayedDao;
import com.toughguy.alarmSystem.service.content.prototype.IDelayedService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;

@Service
public class DelayedServiceImpl extends GenericServiceImpl<Delayed, Integer> implements IDelayedService {

	@Override
	public String selectDelayed(Delayed delayed) {		//需要传递  地域  延迟天数  
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sf.format(date);
		String delayedStart=time.substring(0,7);				//2019-09
		delayed.setDateStart(delayedStart+"-01");				//2019-09-01
		Delayed one = ((IDelayedDao)dao).findOne(delayed);
		if(one==null) {							//没查出来
			delayed.setDateStart(delayedStart+"-01");			//2019-09-01
			delayed.setDateStop(delayedStart+"-10");			//2019-09-10
			delayed.setDelayedStart(time);
			int day = Integer.parseInt(time.substring(5,7));	//月
			String delayedStop =time.substring(0,7)+"-"+(day+(Integer.parseInt(delayed.getDelayedDay())));
			sf.setLenient(false);
			try{
				sf.parse(delayedStop);
			}catch (Exception e) {
				// TODO: handle exception
				return "{ \"success\" : false, \"msg\" : \"请延迟合适的日期\" }";
			}
			delayed.setDelayedStop(delayedStop);
			System.out.println("------------------------"+time.substring(0,7)+"-"+(day+(Integer.parseInt(delayed.getDelayedDay())))+""+"--------------------------------");
			delayed.setState("-1");
			try {
				((IDelayedDao)dao).save(delayed);
				return "{ \"success\" : true }";
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
			}
		}else {
			if(one.getState()=="-1"||one.getState().equals("-1")) {
				return "{ \"success\" : false, \"msg\" : \"只允许延迟一次\" }";
			}else {
				return "{ \"success\" : true }";
			}
		}
	}

	@Override
	public Map<String, String> selectOne(Delayed delayed) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sf.format(date);
		String delayedStart=time.substring(0,7);				//2019-09
		delayed.setDateStart(delayedStart+"-01");				//2019-09-01
		Delayed one = ((IDelayedDao)dao).findOne(delayed);
		String ss = time.substring(8);
		if(one==null && Integer.parseInt(ss)<10  && Integer.parseInt(ss)>=0) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("suucess", "true");
			map.put("value", "规定日期内  可添加");
			return map;
		}else if(one==null && Integer.parseInt(ss)>10){
			Map<String,String> map = new HashMap<String, String>();
			map.put("suucess", "false");
			map.put("value", "超过规定日期，寻求管理员延时添加");
			return map;
		}else if(one !=null && Integer.parseInt(one.getDelayedStop().substring(8))>Integer.parseInt(time.substring(8)) && one.getState().equals("-1")){
			Map<String,String> map = new HashMap<String, String>();
			map.put("suucess", "true");
			map.put("value", "延迟过一次 还在规定时间内");
			map.put("delayedStart", one.getDelayedStart());
			map.put("delayedStop", one.getDelayedStop());
			return map;
		}else if(one !=null && Integer.parseInt(one.getDelayedStop().substring(8))<Integer.parseInt(time.substring(8)) && one.getState().equals("-1")) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("suucess", "true");
			map.put("value", "延迟过一次延时日期已过");
			map.put("delayedStart", one.getDelayedStart());
			map.put("delayedStop", one.getDelayedStop());
			return map;
		}else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("suucess", "false");
			map.put("value", "哪种情况都不是");
			return map;
		}
	}

	@Override
	public Map<String, Object> selectAll() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sf.format(date);
		String delayedStart=time.substring(0,7);				//2019-09
		String ss="%"+delayedStart+"%";
		List<Delayed> list = ((IDelayedDao)dao).selectAll(ss);
		Map<String,Object> map = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			Map<String, String> map2 = new HashMap<>();
			map2.put("city", list.get(i).getXzqh());
			map2.put("oldStartTime", list.get(i).getDateStart());
			map2.put("oldEndTime", list.get(i).getDateStop());
			map2.put("newStartTime", list.get(i).getDelayedStart());
			map2.put("newEndTime", list.get(i).getDelayedStop());
			map.put(list.get(i).getXzqh(), map2);
		}
		return map;
	}

}
