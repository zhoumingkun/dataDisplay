package com.toughguy.dataDisplay.service.content.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.dataDisplay.model.content.ProcessCase;
import com.toughguy.dataDisplay.persist.content.prototype.IProcessCaseDao;
import com.toughguy.dataDisplay.service.content.prototype.IProcessCaseService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;

@Service
public class ProcessCaseServiceImpl extends GenericServiceImpl<ProcessCase, Integer> implements IProcessCaseService{
	
	@Autowired
	private  IProcessCaseDao processCaseDao;
	
	
	@Override
	public Map<String, Object> findProcessCase() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("0.00000");
		DecimalFormat dft = new DecimalFormat("0.00");
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
		String time = sf.format(date);
		ProcessCase total = processCaseDao.findTotal(time);
		if(total!=null) {
			map.put("total", total.getAjsl());
		}else {
			map.put("total", 0);
		}
		
		List<ProcessCase> list = processCaseDao.findCaseCategory(time);
		Map<String,String> hmap = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			if(list.get(i).getAjlbmc()!=null && !list.get(i).getAjlbmc().equals(null) && list.get(i).getAjlbmc()!="") {
				hmap.put(list.get(i).getAjlbmc(), list.get(i).getAjsl());
			}else {
				hmap.put("null", list.get(i).getAjsl());
			}
			
		}
		map.put("caseCategory", hmap);
		List<ProcessCase> arrList = processCaseDao.findCityCaseNum(time);
		List<Map<String,String>> nnmap= new ArrayList<>();
		List<Map<String,String>> ddmap= new ArrayList<>();
		System.out.println(arrList);
		for(int i =0;i<arrList.size();i++) {
			Map<String,String> Nmap = new HashMap<>();
			Map<String,String> Dmap = new HashMap<>();
			Nmap.put("city", arrList.get(i).getSljsdw()+"市");
			Nmap.put("value", arrList.get(i).getAjsl());
			nnmap.add(Nmap);
			int one = Integer.parseInt(arrList.get(i).getAjsl());
			int totalNum = Integer.parseInt(total.getAjsl());
			String format = df.format((float) one/totalNum);
			Double aa = Double.parseDouble(format);
			Dmap.put("city", arrList.get(i).getSljsdw()+"市");
			Dmap.put("value", dft.format(aa*100)+"");
			ddmap.add(Dmap);
		}
		map.put("cityNum", nnmap);
		map.put("cityPercentage", ddmap);
		return map;
		
	}


	@Override
	public Map<String, Object> findProcessCaseHB(String tjTime) {
		// TODO Auto-generated method stub
		 List<ProcessCase> list = processCaseDao.findProcessCaseHB(tjTime);
		 DecimalFormat df = new DecimalFormat("0.0");
		 Map<String,Object> map = new HashMap<>();
		 for(int i=0;i<list.size();i++) {
			 String ajsl = list.get(i).getAjsl();
			 Double valueOf = Double.valueOf(ajsl);
			 String format = df.format(valueOf*100);
			 map.put("hb",format );
		 }
		 
		 return map;
	}

}
