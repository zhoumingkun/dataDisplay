package com.toughguy.alarmSystem.service.content.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.model.content.Saoheichue;
import com.toughguy.alarmSystem.persist.content.prototype.IBaojingqingkuangDao;
import com.toughguy.alarmSystem.persist.content.prototype.ISaoheichueDao;
import com.toughguy.alarmSystem.service.content.prototype.ISaoheichueService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;
import com.toughguy.alarmSystem.util.POIUtils;


/**
 * 扫黑除恶Service实现类
 * @author zmk
 *
 */
@Service
public class SaoheichueServiceImpl extends GenericServiceImpl<Saoheichue, Integer> implements ISaoheichueService{
	@Autowired
	ISaoheichueDao  saoheichueDao;

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
		Saoheichue sh = ((ISaoheichueDao)dao).findAllSH(map);
		if(sh==null) {
			Map<String,Saoheichue> shce = new HashMap<>();
			Saoheichue sc = new Saoheichue();
			sc.setShcedzxs(0);
			sc.setDjqbfzxs(0);
			sc.setDjwwfzxs(0);
			sc.setPhstfzxs(0);
			sc.setFfjzfzxs(0);
			sc.setDxwlfzxs(0);
			shce.put("shce", sc);
			return shce ;
		}
		Map<String,Saoheichue> shce = new HashMap<>();
		shce.put("shce", sh);
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
		int month2 = Integer.parseInt(month)-1;					//月+1
		int year2=Integer.parseInt(year)-1;						//年-1
		String s="";
		if(month2<10) {
			s ="0"+month2;
		}else {
			s=month2+"";
		}
		String stoptime=year+"-"+s+"-"+"01";
		if(month=="01" || month.equals("01")) {
			stoptime=year2+"-12-01";
		}
		map.put("starttime", stoptime);		//2019-08-01
		map.put("stoptime", starttime);		//2019-09-01
		map.put("xzqh", xzqh);
		Saoheichue sh = ((ISaoheichueDao)dao).findOneSH(map);		//看当前月是否已经有数据了
		if(sh==null) {
			Map<String,Saoheichue> shce = new HashMap<>();
			Saoheichue sc = new Saoheichue();
			sc.setShcedzxs(0);
			sc.setDjqbfzxs(0);
			sc.setDjwwfzxs(0);
			sc.setPhstfzxs(0);
			sc.setFfjzfzxs(0);
			sc.setDxwlfzxs(0);
			shce.put("djshce", sc);
			return shce ;
		}
		Map<String,Saoheichue> djshce = new HashMap<>();
		djshce.put("djshce", sh);
		return djshce ;
		/*if(saoheichue==null) {		//截至当前还未提交
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
		}*/
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
				aaa.put("createtime", list.get(i).getTbdw());
				aaa.put("id", list.get(i).getId()+"");
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
				aaa.put("id", list.get(i).getId()+"");
				saoheichue.put(list.get(i).getTjyf().substring(0, 7), aaa);
			}
			return saoheichue;
		}
		
	}

	@Override
	public Map<String,Object> selectAll(String time) {
		// TODO Auto-generated method stub
		String date = "%"+time+"%";
		List<Saoheichue> list = ((ISaoheichueDao)dao).selectAll(date);
		
		Map<String,String> a = new HashMap<>();
		Map<String,Object> aa = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			a.put(list.get(i).getXzqh(),list.get(i).getShcedzxs()+"");
		}
		aa.put("shcedzxs", a);
		
		Map<String,String> b = new HashMap<>();
		Map<String,Object> bb = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			b.put(list.get(i).getXzqh(),list.get(i).getDjqbfzxs()+"");
		}
		bb.put("djqbfzxs", b);


		Map<String,String> j = new HashMap<>();
		Map<String,Object> jj = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			j.put(list.get(i).getXzqh(),list.get(i).getDjwwfzxs()+"");
		}
		jj.put("djwwfzxs", j);
		
		Map<String,String> k = new HashMap<>();
		Map<String,Object> kk = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			k.put(list.get(i).getXzqh(),list.get(i).getPhstfzxs()+"");
		}
		kk.put("phstfzxs", k);
		
		Map<String,String> c = new HashMap<>();
		Map<String,Object> cc = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			c.put(list.get(i).getXzqh(),list.get(i).getFfjzfzxs()+"");
		}
		cc.put("ffjzfzxs", c);
		
		Map<String,String> d = new HashMap<>();
		Map<String,Object> dd = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			d.put(list.get(i).getXzqh(),list.get(i).getDxwlfzxs()+"");
		}
		dd.put("dxwlfzxs", d);
		
		Map<String,String> e = new HashMap<>();
		Map<String,Object> ee = new HashMap<>();
		/*for(int i =0;i<list.size();i++) {
			e.put(list.get(i).getXzqh(),list.get(i).getHj()+"");
		}
		ee.put("hj", a);*/
		
		String tjyf=time;
		Saoheichue hj = ((ISaoheichueDao)dao).findShenHj(tjyf);
		ee.put("hj", hj);
		Map<String,String> f = new HashMap<>();
		Map<String,Object> ff = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			f.put(list.get(i).getXzqh(),list.get(i).getTbr()+"");
		}
		ff.put("tbr", f);
		
		Map<String,String> g = new HashMap<>();
		Map<String,Object> gg = new HashMap<>();
		for(int i =0;i<list.size();i++) {
			g.put(list.get(i).getXzqh(),list.get(i).getTjyf()+"");
		}
		gg.put("tjyf", g);
		
		Map<String,Object> boss=new HashMap<>();
		boss.put("shcedzxs", aa);
		boss.put("djqbfzxs", bb);
		boss.put("djwwfzxs", jj);
		boss.put("phstfzxs", kk);
		boss.put("ffjzfzxs", cc);
		boss.put("dxwlfzxs", dd);
		boss.put("hj", ee);
		boss.put("tbr", ff);
		boss.put("tjyf", gg);
		return boss;
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

	@Override
	public void updateAllShen(Saoheichue saoheichue) {
		// TODO Auto-generated method stub
		((ISaoheichueDao)dao).updateAllShen(saoheichue);
	}
	
	//导出省扫黑除恶表
	@Override
	public void ExportShenShce(HttpServletResponse response, String tjyf) {
        try {
			
			//输入模板文件
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("upload/base/扫黑除恶表（模板）.xlsx"));
			SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
			workbook.setCompressTempFiles(false);
			POIUtils utils = new POIUtils();
			//对应Excel文件中的sheet，0代表第一个
			Sheet sh = xssfWorkbook.getSheetAt(0); 
			
			Map<String,String> map = new HashMap<>();
			map.put("tjyf", tjyf);
			List<Saoheichue> soaheichueReport = saoheichueDao.findByTjyfTime(map);
			System.out.println(soaheichueReport);
			System.out.println(soaheichueReport.size());
			Date date = new Date();
			SimpleDateFormat saoheiTime = new SimpleDateFormat("yyyy-MM-dd");
			String time = saoheiTime.format(date);
			String year =tjyf.substring(0,4);			//2019
			String month =tjyf.substring(5,7);         //09
			Row row0 = sh.createRow(0);
			CellRangeAddress region0 = new CellRangeAddress(0, (short) 0, 0, (short) 7);
			Cell cell00000=row0.createCell(0);
			utils.setRegionStyle(sh, region0, utils.Style10(workbook));
			sh.addMergedRegion(region0);
	        cell00000.setCellValue(year+"年"+month+"月"+"扫黑除恶等专项行动有关警情线索统计表");//填报时间（当前导出时间）
//			sh.getRow(1).getCell(7).setCellValue(time); 	//填报人（暂无）
			//第一行数据内容
//			Row row1 = sh.createRow(1);
//			CellRangeAddress region = new CellRangeAddress(1, (short) 1, 0, (short) 2);
//			Cell cell01=row1.createCell(0);
//			utils.setRegionStyle(sh, region, utils.Style81(workbook));
//			sh.addMergedRegion(region);
//			cell01.setCellValue("填报时间："+year+"年"+month+"月");//填报时间（当前导出时间）
			
//	        Cell cell02=row1.createCell(1);
//			cell02.setCellStyle(utils.Style8(workbook));
//	        cell02.setCellValue(year+"年"+month+"月");//填报时间（当前导出时间）
	        
//	        Cell cell03=row1.createCell(6);
//			cell03.setCellStyle(utils.Style8(workbook));
//	        cell03.setCellValue("填报人：");//填报人（暂无）
//	        
//			Cell cell04=row1.createCell(7);
//			cell04.setCellStyle(utils.Style8(workbook));
//	        cell04.setCellValue("苏鹏琪");//填报人（暂无）
	        
			for(int j=0; j<soaheichueReport.size(); j++) {
				if(soaheichueReport.get(j).getXzqh().equals("太原")){
					System.out.println("进来啦");
					Row row3 = sh.createRow(3);
					row3.setHeightInPoints(30);
					Cell cell00=row3.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("1");
		            
		            Cell cell000=row3.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("太原");
		            
					Cell cell1=row3.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row3.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row3.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row3.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row3.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row3.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		            
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("大同")){
					Row row4 = sh.createRow(4);
					row4.setHeightInPoints(30);
					Cell cell00=row4.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("2");
		            
		            Cell cell000=row4.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("大同");
		            
					Cell cell1=row4.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row4.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row4.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row4.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row4.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row4.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("朔州")){
					Row row5 = sh.createRow(5);
					row5.setHeightInPoints(30);
					Cell cell00=row5.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("3");
		            
		            Cell cell000=row5.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("朔州");
		            
					Cell cell1=row5.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row5.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row5.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row5.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row5.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row5.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("忻州")){
					Row row6 = sh.createRow(6);
					row6.setHeightInPoints(30);
					Cell cell00=row6.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("4");
		            
		            Cell cell000=row6.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("忻州");
		            
					Cell cell1=row6.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row6.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row6.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row6.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row6.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row6.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("吕梁")){
					Row row7 = sh.createRow(7);
					row7.setHeightInPoints(30);
					Cell cell00=row7.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("5");
		            
		            Cell cell000=row7.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("吕梁");
		            
					Cell cell1=row7.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row7.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row7.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row7.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row7.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row7.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("晋中")){
					Row row8 = sh.createRow(8);
					row8.setHeightInPoints(30);
					Cell cell00=row8.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("6");
		            
		            Cell cell000=row8.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("晋中");
		            
					Cell cell1=row8.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row8.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row8.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row8.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row8.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row8.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("阳泉")){
					Row row9 = sh.createRow(9);
					row9.setHeightInPoints(30);
					Cell cell00=row9.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("7");
		            
		            Cell cell000=row9.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("阳泉");
		            
					Cell cell1=row9.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row9.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row9.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row9.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row9.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row9.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("长治")){
					Row row10 = sh.createRow(10);
					row10.setHeightInPoints(30);
					Cell cell00=row10.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("8");
		            
		            Cell cell000=row10.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("长治");
		            
					Cell cell1=row10.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row10.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row10.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row10.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row10.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row10.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("晋城")){
					Row row11 = sh.createRow(11);
					row11.setHeightInPoints(30);
					Cell cell00=row11.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("9");
		            
		            Cell cell000=row11.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("晋城");
		            
					Cell cell1=row11.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row11.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row11.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row11.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row11.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row11.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("临汾")){
					Row row12 = sh.createRow(12);
					row12.setHeightInPoints(30);
					Cell cell00=row12.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("10");
		            
		            Cell cell000=row12.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("临汾");
		            
					Cell cell1=row12.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row12.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row12.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row12.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row12.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row12.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				else if(soaheichueReport.get(j).getXzqh().equals("运城")){
					Row row13 = sh.createRow(13);
					row13.setHeightInPoints(30);
					Cell cell00=row13.createCell(0);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue("11");
		            
		            Cell cell000=row13.createCell(1);
		            cell000.setCellStyle(utils.Style6(workbook));
		            cell000.setCellValue("运城");
		            
					Cell cell1=row13.createCell(2);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(soaheichueReport.get(j).getShcedzxs());
		            
		            Cell cell2=row13.createCell(3);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(soaheichueReport.get(j).getDjqbfzxs());
		            
		            Cell cell3=row13.createCell(4);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(soaheichueReport.get(j).getDjwwfzxs());
		            
		            Cell cell4=row13.createCell(5);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(soaheichueReport.get(j).getPhstfzxs());
		            
		            Cell cell5=row13.createCell(6);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(soaheichueReport.get(j).getFfjzfzxs());
		            
		            Cell cell6=row13.createCell(7);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(soaheichueReport.get(j).getDxwlfzxs());
		        	}
				Saoheichue saoheichue = saoheichueDao.findShenHj(tjyf);
				//合计1
		        Row row14 = sh.createRow(14);
		        row14.setHeightInPoints(30);
		        CellRangeAddress region1 = new CellRangeAddress(14, (short) 14, 0, (short) 1);
				Cell cell0001 = row14.createCell(0);
				utils.setRegionStyle(sh, region1, utils.Style6(workbook));
				sh.addMergedRegion(region1);
				cell0001.setCellValue("合计");
				
		        Cell cell1=row14.createCell(2);
				cell1.setCellStyle(utils.Style6(workbook));
				cell1.setCellValue(saoheichue.getShcedzxs());
				//合计2
		        Cell cell2=row14.createCell(3);
				cell2.setCellStyle(utils.Style6(workbook));
				cell2.setCellValue(saoheichue.getDjqbfzxs());
				//合计3
		        Cell cell3=row14.createCell(4);
				cell3.setCellStyle(utils.Style6(workbook));
				cell3.setCellValue(saoheichue.getDjwwfzxs());
				//合计4
		        Cell cell4=row14.createCell(5);
				cell4.setCellStyle(utils.Style6(workbook));
				cell4.setCellValue(saoheichue.getPhstfzxs());
				//合计5
		        Cell cell5=row14.createCell(6);
				cell5.setCellStyle(utils.Style6(workbook));
				cell5.setCellValue(saoheichue.getFfjzfzxs());
				//合计6
		        Cell cell6=row14.createCell(7);
				cell6.setCellStyle(utils.Style6(workbook));
				cell6.setCellValue(saoheichue.getDxwlfzxs());
				
				Row row15 = sh.createRow(15);
				Cell cell7=row15.createCell(7);
				cell7.setCellStyle(utils.Style83(workbook));
				cell7.setCellValue("单位：起");
				
			}
		
			String year2 =tjyf.substring(0,4);			//2019
			String month2 =tjyf.substring(5,7);         //09
			String title =year2+"年"+month2+"月"+"扫黑除恶等专项行动有关警情线索统计表";
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+new String( title.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        out.flush();
			workbook.write(out);
	        out.close();
	        workbook.dispose();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//导出市扫黑除恶表
	@Override
	public void ExportShiShce(HttpServletResponse response, String tjyf, String xzqh) {
try {
			
			//输入模板文件
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("upload/base/扫黑除恶表（模板）.xlsx"));
			SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
			workbook.setCompressTempFiles(false);
			POIUtils utils = new POIUtils();
			//对应Excel文件中的sheet，0代表第一个
			Sheet sh = xssfWorkbook.getSheetAt(0); 
			
			Map<String,String> map = new HashMap<>();
			map.put("tjyf", tjyf);
			map.put("xzqh", xzqh);
			Saoheichue soaheichueReport = saoheichueDao.findByTjyfAndRegion(map);
			System.out.println(soaheichueReport);
//			Date date = new Date();
//			SimpleDateFormat saoheiTime = new SimpleDateFormat("yyyy-MM-dd");
//			String time = saoheiTime.format(date);
			String year =tjyf.substring(0,4);			//2019
			String month =tjyf.substring(5,7);         //09
//			sh.getRow(1).getCell(7).setCellValue(time); 	//填报人（暂无）
			Row row0 = sh.createRow(0);
			CellRangeAddress region0 = new CellRangeAddress(0, (short) 0, 0, (short) 7);
			Cell cell00000=row0.createCell(0);
			utils.setRegionStyle(sh, region0, utils.Style10(workbook));
			sh.addMergedRegion(region0);
			cell00000.setCellValue(year+"年"+month+"月"+"扫黑除恶等专项行动有关警情线索统计表");//填报时间（当前导出时间）
			//第一行数据内容
//			Row row1 = sh.createRow(1);
//			Cell cell01=row1.createCell(0);
//			cell01.setCellStyle(utils.Style8(workbook));
//	        cell01.setCellValue("填报时间：");//填报时间（当前导出时间）
//	        
//	        Cell cell02=row1.createCell(1);
//			cell02.setCellStyle(utils.Style8(workbook));
//	        cell02.setCellValue(year+"年"+month+"月");//填报时间（当前导出时间）
			Row row1 = sh.createRow(1);
			row1.setHeightInPoints(30);
			CellRangeAddress region = new CellRangeAddress(1, (short) 1, 0, (short) 2);
			Cell cell01=row1.createCell(0);
			utils.setRegionStyle(sh, region, utils.Style81(workbook));
			sh.addMergedRegion(region);
			Date date = new Date();
			SimpleDateFormat saoheiTime = new SimpleDateFormat("yyyy-MM-dd");
			String time = saoheiTime.format(soaheichueReport.getCreateTime());
			cell01.setCellValue("填报时间："+time);//填报时间（当前导出时间）
			
	        Cell cell03=row1.createCell(7);
			cell03.setCellStyle(utils.Style8(workbook));
	        cell03.setCellValue("填报人："+soaheichueReport.getTbr());//填报人（暂无）
	        
//			Cell cell04=row1.createCell(7);
//			cell04.setCellStyle(utils.Style8(workbook));
//	        cell04.setCellValue(soaheichueReport.getTbr());//填报人（暂无）
	        
	        if(soaheichueReport.getXzqh().equals("太原")){
				System.out.println("进来啦");
				Row row3 = sh.createRow(3);
				row3.setHeightInPoints(30);
				Cell cell00=row3.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("1");
	            
	            Cell cell000=row3.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("太原");
	            
				Cell cell1=row3.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row3.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row3.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row3.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row3.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row3.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	            
	        	}
			else if(soaheichueReport.getXzqh().equals("大同")){
				Row row4 = sh.createRow(4);
				row4.setHeightInPoints(30);
				Cell cell00=row4.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("2");
	            
	            Cell cell000=row4.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("大同");
	            
				Cell cell1=row4.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row4.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row4.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row4.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row4.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row4.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("朔州")){
				Row row5 = sh.createRow(5);
				row5.setHeightInPoints(30);
				Cell cell00=row5.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("3");
	            
	            Cell cell000=row5.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("朔州");
	            
				Cell cell1=row5.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row5.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row5.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row5.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row5.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row5.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("忻州")){
				Row row6 = sh.createRow(6);
				row6.setHeightInPoints(30);
				Cell cell00=row6.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("4");
	            
	            Cell cell000=row6.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("忻州");
	            
	            
				Cell cell1=row6.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row6.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row6.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row6.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row6.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row6.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("吕梁")){
				Row row7 = sh.createRow(7);
				row7.setHeightInPoints(30);
				Cell cell00=row7.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("5");
	            
	            Cell cell000=row7.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("吕梁");
	            
				Cell cell1=row7.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row7.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row7.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row7.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row7.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row7.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("晋中")){
				Row row8 = sh.createRow(8);
				row8.setHeightInPoints(30);
				Cell cell00=row8.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("6");
	            
	            Cell cell000=row8.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("晋中");
	            
				Cell cell1=row8.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row8.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row8.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row8.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row8.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row8.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("阳泉")){
				Row row9 = sh.createRow(9);
				row9.setHeightInPoints(30);
				Cell cell00=row9.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("7");
	            
	            Cell cell000=row9.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("阳泉");
	            
				Cell cell1=row9.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row9.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row9.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row9.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row9.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row9.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("长治")){
				Row row10 = sh.createRow(10);
				row10.setHeightInPoints(30);
				Cell cell00=row10.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("8");
	            
	            Cell cell000=row10.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("长治");
	            
				Cell cell1=row10.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row10.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row10.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row10.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row10.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row10.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("晋城")){
				Row row11 = sh.createRow(11);
				row11.setHeightInPoints(30);
				Cell cell00=row11.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("9");
	            
	            Cell cell000=row11.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("晋城");
	            
				Cell cell1=row11.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row11.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row11.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row11.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row11.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row11.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("临汾")){
				Row row12 = sh.createRow(12);
				row12.setHeightInPoints(30);
				Cell cell00=row12.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("10");
	            
	            Cell cell000=row12.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("临汾");
				Cell cell1=row12.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row12.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row12.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row12.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row12.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row12.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
			else if(soaheichueReport.getXzqh().equals("运城")){
				Row row13 = sh.createRow(13);
				row13.setHeightInPoints(30);
				Cell cell00=row13.createCell(0);
	            cell00.setCellStyle(utils.Style6(workbook));
	            cell00.setCellValue("11");
	            
	            Cell cell000=row13.createCell(1);
	            cell000.setCellStyle(utils.Style6(workbook));
	            cell000.setCellValue("运城");
	            
				Cell cell1=row13.createCell(2);
	            cell1.setCellStyle(utils.Style6(workbook));
	            cell1.setCellValue(soaheichueReport.getShcedzxs());
	            
	            Cell cell2=row13.createCell(3);
	            cell2.setCellStyle(utils.Style6(workbook));
	            cell2.setCellValue(soaheichueReport.getDjqbfzxs());
	            
	            Cell cell3=row13.createCell(4);
	            cell3.setCellStyle(utils.Style6(workbook));
	            cell3.setCellValue(soaheichueReport.getDjwwfzxs());
	            
	            Cell cell4=row13.createCell(5);
	            cell4.setCellStyle(utils.Style6(workbook));
	            cell4.setCellValue(soaheichueReport.getPhstfzxs());
	            
	            Cell cell5=row13.createCell(6);
	            cell5.setCellStyle(utils.Style6(workbook));
	            cell5.setCellValue(soaheichueReport.getFfjzfzxs());
	            
	            Cell cell6=row13.createCell(7);
	            cell6.setCellStyle(utils.Style6(workbook));
	            cell6.setCellValue(soaheichueReport.getDxwlfzxs());
	        	}
	      //合计1
	        Row row14 = sh.createRow(14);
	        row14.setHeightInPoints(30);
			CellRangeAddress region1 = new CellRangeAddress(14, (short) 14, 0, (short) 1);
			Cell cell0001 = row14.createCell(0);
			utils.setRegionStyle(sh, region1, utils.Style6(workbook));
			sh.addMergedRegion(region1);
			cell0001.setCellValue("合计:");
			
	        Cell cell1=row14.createCell(2);
			cell1.setCellStyle(utils.Style6(workbook));
			cell1.setCellValue(soaheichueReport.getShcedzxs());
			//合计2
	        Cell cell2=row14.createCell(3);
			cell2.setCellStyle(utils.Style6(workbook));
			cell2.setCellValue(soaheichueReport.getDjqbfzxs());
			//合计3
	        Cell cell3=row14.createCell(4);
			cell3.setCellStyle(utils.Style6(workbook));
			cell3.setCellValue(soaheichueReport.getDjwwfzxs());
			//合计4
	        Cell cell4=row14.createCell(5);
			cell4.setCellStyle(utils.Style6(workbook));
			cell4.setCellValue(soaheichueReport.getPhstfzxs());
			//合计5
	        Cell cell5=row14.createCell(6);
			cell5.setCellStyle(utils.Style6(workbook));
			cell5.setCellValue(soaheichueReport.getFfjzfzxs());
			//合计6
	        Cell cell6=row14.createCell(7);
			cell6.setCellStyle(utils.Style6(workbook));
			cell6.setCellValue(soaheichueReport.getDxwlfzxs());
			
			Row row15 = sh.createRow(15);
			Cell cell7=row15.createCell(7);
			cell7.setCellStyle(utils.Style83(workbook));
			cell7.setCellValue("单位：起");

			String year2 =tjyf.substring(0,4);			//2019
			String month2 =tjyf.substring(5,7);         //09
			String title =year2+"年"+month2+"月"+"扫黑除恶等专项行动有关警情线索统计表";
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+new String( title.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        out.flush();
			workbook.write(out);
	        out.close();
	        workbook.dispose();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
