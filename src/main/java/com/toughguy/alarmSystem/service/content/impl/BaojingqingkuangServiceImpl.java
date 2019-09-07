package com.toughguy.alarmSystem.service.content.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.toughguy.alarmSystem.model.content.Baojingqingkuang;
import com.toughguy.alarmSystem.persist.content.prototype.IBaojingqingkuangDao;
import com.toughguy.alarmSystem.service.content.prototype.IBaojingqingkuangService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;
import com.toughguy.alarmSystem.util.POIUtils;


/**
 * 报警情况Service实现类
 * @author zmk
 *
 */
@Service
public class BaojingqingkuangServiceImpl extends GenericServiceImpl<Baojingqingkuang, Integer> implements IBaojingqingkuangService{
	
	
	@Autowired
	IBaojingqingkuangDao  baojingqingkuangDao;

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


	public List<Baojingqingkuang> findByStartEndTime(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ((IBaojingqingkuangDao)dao).findByTjyfTime(map);
	}
	
	//导出省报警情况表
	public void ExportShenBjqk(HttpServletResponse response, String tjyf){
		try {
			
			//输入模板文件
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("upload/base/报警情况统计月报表（模板） .xlsx"));
			SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
			workbook.setCompressTempFiles(false);
			POIUtils utils = new POIUtils();
			//对应Excel文件中的sheet，0代表第一个
			Sheet sh = xssfWorkbook.getSheetAt(0); 
			
			Map<String,String> map = new HashMap<>();
			map.put("tjyf", tjyf);
			
			List<Baojingqingkuang> BaojingReport = baojingqingkuangDao.findByTjyfTime(map);
			System.out.println(BaojingReport);
			System.out.println(BaojingReport.size());
			Date date = new Date();
			SimpleDateFormat dateBaojing = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateBaojing.format(date);
			String year =tjyf.substring(0,4);			//2019
			String month =tjyf.substring(5,7);         //09
			//第一行数据内容
			Row row1 = sh.createRow(1);
			Cell cell02=row1.createCell(0);
			cell02.setCellStyle(utils.Style8(workbook));
	        cell02.setCellValue("填报单位:");//填报时间（当前导出时间）
	        
	        Cell cell03=row1.createCell(1);
			cell03.setCellStyle(utils.Style8(workbook));
	        cell03.setCellValue("山西省公安厅");//填报时间（当前导出时间）
	        
			Cell cell0=row1.createCell(7);
			cell0.setCellStyle(utils.Style8(workbook));
	        cell0.setCellValue(year+"年"+month+"月");//填报时间（当前导出时间）
	        
	        Cell cell01=row1.createCell(16);
			cell01.setCellStyle(utils.Style8(workbook));
	        cell01.setCellValue("苏鹏琪");////填报人
	        
	        Baojingqingkuang baojingHj = baojingqingkuangDao.findShenHj(tjyf);
	        //合计1
	        Row row4 = sh.createRow(4);
	        Cell cell04=row4.createCell(3);
			cell04.setCellStyle(utils.Style6(workbook));
			cell04.setCellValue(baojingHj.getHj());
			//合计2
	        Cell cell05=row4.createCell(4);
			cell05.setCellStyle(utils.Style6(workbook));
			cell05.setCellValue(baojingHj.getWffzaj());
			//合计3
	        Cell cell06=row4.createCell(5);
			cell06.setCellStyle(utils.Style6(workbook));
			cell06.setCellValue(baojingHj.getZaaj());
			//合计4
	        Cell cell07=row4.createCell(6);
			cell07.setCellStyle(utils.Style6(workbook));
			cell07.setCellValue(baojingHj.getHzsg());
			//合计5
	        Cell cell08=row4.createCell(7);
			cell08.setCellStyle(utils.Style6(workbook));
			cell08.setCellValue(baojingHj.getJtsg());
			//合计6
	        Cell cell09=row4.createCell(8);
			cell09.setCellStyle(utils.Style6(workbook));
			cell09.setCellValue(baojingHj.getZazhsg());
			//合计7
	        Cell cell010=row4.createCell(9);
			cell010.setCellStyle(utils.Style6(workbook));
			cell010.setCellValue(baojingHj.getZhsg());
			//合计8
	        Cell cell012=row4.createCell(10);
			cell012.setCellStyle(utils.Style6(workbook));
			cell012.setCellValue(baojingHj.getZs());
			//合计9
	        Cell cell013=row4.createCell(11);
			cell013.setCellStyle(utils.Style6(workbook));
			cell013.setCellValue(baojingHj.getJf());
			//合计10
	        Cell cell014=row4.createCell(12);
			cell014.setCellStyle(utils.Style6(workbook));
			cell014.setCellValue(baojingHj.getJtbl());
			//合计11
	        Cell cell015=row4.createCell(13);
			cell015.setCellStyle(utils.Style6(workbook));
			cell015.setCellValue(baojingHj.getGmqz());
			//合计12
	        Cell cell016=row4.createCell(14);
			cell016.setCellStyle(utils.Style6(workbook));
			cell016.setCellValue(baojingHj.getZsxr());
			//合计13
	        Cell cell017=row4.createCell(15);
			cell017.setCellStyle(utils.Style6(workbook));
			cell017.setCellValue(baojingHj.getJwjd());
			//合计14
	        Cell cell018=row4.createCell(16);
			cell018.setCellStyle(utils.Style6(workbook));
			cell018.setCellValue(baojingHj.getQt());
	        
			for(int j=0; j<BaojingReport.size(); j++) {
				if(BaojingReport.get(j).getBjqk().equals("110接警")){
					System.out.println("进来啦");
					Row row5 = sh.createRow(5);
					Cell cell00=row5.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row5.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row5.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row5.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row5.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row5.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row5.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row5.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row5.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row5.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row5.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row5.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row5.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row5.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("执勤巡逻发现")){
					Row row6 = sh.createRow(6);
					Cell cell00=row6.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row6.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row6.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row6.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row6.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row6.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row6.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row6.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row6.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row6.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row6.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row6.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row6.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row6.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("器材报警")){
					Row row7 = sh.createRow(7);
					Cell cell00=row7.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row7.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row7.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row7.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row7.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row7.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row7.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row7.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row7.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row7.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row7.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row7.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row7.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row7.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("口头报警")){
					Row row8 = sh.createRow(8);
					Cell cell00=row8.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row8.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row8.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row8.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row8.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row8.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row8.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row8.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row8.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row8.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row8.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row8.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row8.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row8.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("电话报警")){
					Row row9 = sh.createRow(9);
					Cell cell00=row9.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row9.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row9.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row9.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row9.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row9.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row9.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row9.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row9.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row9.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row9.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row9.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row9.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row9.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("短信微信报警")){
					Row row10 = sh.createRow(10);
					Cell cell00=row10.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getWffzaj());
		            
					Cell cell1=row10.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row10.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row10.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row10.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row10.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row10.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row10.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row10.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row10.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row10.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row10.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row10.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row10.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("举报")){
					Row row11 = sh.createRow(11);
					Cell cell00=row11.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row11.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row11.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row11.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row11.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row11.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row11.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row11.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row11.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row11.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row11.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row11.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row11.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row11.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("扭送现行")){
					Row row12 = sh.createRow(12);
					Cell cell00=row12.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row12.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row12.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row12.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row12.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row12.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row12.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row12.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row12.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row12.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row12.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row12.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row12.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row12.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("投案自首")){
					Row row13 = sh.createRow(13);
					Cell cell00=row13.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row13.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row13.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row13.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row13.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row13.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row13.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row13.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row13.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row13.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row13.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row13.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row13.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row13.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("其他部门移送")){
					Row row14 = sh.createRow(14);
					Cell cell00=row14.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row14.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row14.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row14.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row14.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row14.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row14.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row14.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row14.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row14.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row14.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row14.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row14.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row14.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("其他")){
					Row row15 = sh.createRow(15);
					Cell cell00=row15.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row15.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row15.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row15.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row15.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row15.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row15.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row15.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row15.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row15.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row15.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row15.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row15.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row15.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("出动警力")){
					Row row16 = sh.createRow(16);
					Cell cell00=row16.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row16.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row16.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row16.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row16.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row16.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row16.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row16.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row16.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row16.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row16.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row16.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row16.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row16.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("处置报警(起)")){
					Row row17 = sh.createRow(17);
					Cell cell00=row17.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row17.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row17.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row17.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row17.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row17.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row17.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row17.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row17.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row17.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row17.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row17.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row17.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row17.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("现场抓获违法犯罪人员(人)")){
					Row row18 = sh.createRow(18);
					Cell cell00=row18.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row18.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row18.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row18.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row18.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row18.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row18.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row18.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row18.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row18.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row18.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row18.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row18.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row18.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("逃犯(人)")){
					Row row19 = sh.createRow(19);
					Cell cell00=row19.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row19.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row19.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row19.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row19.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row19.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row19.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row19.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row19.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row19.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row19.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row19.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row19.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row19.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("救助伤员(人)")){
					Row row20 = sh.createRow(20);
					Cell cell00=row20.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row20.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row20.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row20.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row20.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row20.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row20.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row20.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row20.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row20.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row20.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row20.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row20.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row20.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("救助群众(人)")){
					Row row21 = sh.createRow(21);
					Cell cell00=row21.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row21.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row21.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row21.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row21.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row21.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row21.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row21.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row21.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row21.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row21.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row21.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row21.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row21.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("继续盘问(人)")){
					Row row22 = sh.createRow(22);
					Cell cell00=row22.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row22.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row22.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row22.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row22.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row22.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row22.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row22.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row22.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row22.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row22.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row22.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row22.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row22.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("无效报警(起)")){
					Row row23 = sh.createRow(23);
					Cell cell00=row23.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row23.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row23.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row23.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row23.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row23.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row23.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row23.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row23.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row23.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row23.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row23.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row23.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row23.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("死亡(人)")){
					Row row24 = sh.createRow(24);
					Cell cell00=row24.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row24.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row24.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row24.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row24.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row24.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row24.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row24.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row24.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row24.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row24.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row24.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row24.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row24.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
				else if(BaojingReport.get(j).getBjqk().equals("受伤(人)")){
					Row row25 = sh.createRow(25);
					Cell cell00=row25.createCell(3);
		            cell00.setCellStyle(utils.Style6(workbook));
		            cell00.setCellValue(BaojingReport.get(j).getHj());
		            
					Cell cell1=row25.createCell(4);
		            cell1.setCellStyle(utils.Style6(workbook));
		            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
		            
		            Cell cell2=row25.createCell(5);
		            cell2.setCellStyle(utils.Style6(workbook));
		            cell2.setCellValue(BaojingReport.get(j).getZaaj());
		            
		            Cell cell3=row25.createCell(6);
		            cell3.setCellStyle(utils.Style6(workbook));
		            cell3.setCellValue(BaojingReport.get(j).getHzsg());
		            
		            Cell cell4=row25.createCell(7);
		            cell4.setCellStyle(utils.Style6(workbook));
		            cell4.setCellValue(BaojingReport.get(j).getJtsg());
		            
		            Cell cell5=row25.createCell(8);
		            cell5.setCellStyle(utils.Style6(workbook));
		            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
		            
		            Cell cell6=row25.createCell(9);
		            cell6.setCellStyle(utils.Style6(workbook));
		            cell6.setCellValue(BaojingReport.get(j).getZhsg());
		            
		            Cell cell7=row25.createCell(10);
		            cell7.setCellStyle(utils.Style6(workbook));
		            cell7.setCellValue(BaojingReport.get(j).getZs());
		            
		            Cell cell8=row25.createCell(11);
		            cell8.setCellStyle(utils.Style6(workbook));
		            cell8.setCellValue(BaojingReport.get(j).getJf());
		            
		            Cell cell9=row25.createCell(12);
		            cell9.setCellStyle(utils.Style6(workbook));
		            cell9.setCellValue(BaojingReport.get(j).getJtbl());
		            
		            Cell cell10=row25.createCell(13);
		            cell10.setCellStyle(utils.Style6(workbook));
		            cell10.setCellValue(BaojingReport.get(j).getGmqz());
		            
		            Cell cell11=row25.createCell(14);
		            cell11.setCellStyle(utils.Style6(workbook));
		            cell11.setCellValue(BaojingReport.get(j).getZsxr());
		            
		            Cell cell12=row25.createCell(15);
		            cell12.setCellStyle(utils.Style6(workbook));
		            cell12.setCellValue(BaojingReport.get(j).getJwjd());
		            
		            Cell cell13=row25.createCell(16);
		            cell13.setCellStyle(utils.Style6(workbook));
		            cell13.setCellValue(BaojingReport.get(j).getQt());
		        	}
			}
			String year1 =tjyf.substring(0,4);			//2019
			String month2 =tjyf.substring(5,7);         //09
			String title = year1+"年"+month2+"月"+"报警情况统计月报表";
			
			OutputStream out = response.getOutputStream();
//			response.reset();
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
	
	
	
	//导出市报警情况表
		public void ExportShiBjqk(HttpServletResponse response, String tjyf, String xzqh){
			try {
				
				//输入模板文件
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("upload/base/报警情况统计月报表（模板） .xlsx"));
				SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
				workbook.setCompressTempFiles(false);
				POIUtils utils = new POIUtils();
				//对应Excel文件中的sheet，0代表第一个
				Sheet sh = xssfWorkbook.getSheetAt(0); 
				
				Map<String,String> map = new HashMap<>();
				map.put("tjyf", tjyf);
				map.put("xzqh", xzqh);
				List<Baojingqingkuang> BaojingReport = baojingqingkuangDao.findByTjyfAndRegion(map);
				System.out.println(BaojingReport);
				System.out.println(BaojingReport.size());
				Date date = new Date();
				SimpleDateFormat dateBaojing = new SimpleDateFormat("yyyy-MM-dd");
				String time = dateBaojing.format(date);
				String year =tjyf.substring(0,4);			//2019
				String month =tjyf.substring(5,7);         //09
				//第一行数据内容
				Row row1 = sh.createRow(1);
				Cell cell02=row1.createCell(0);
				cell02.setCellStyle(utils.Style8(workbook));
		        cell02.setCellValue("填报单位:");//填报时间（当前导出时间）
		        
		        Cell cell03=row1.createCell(1);
				cell03.setCellStyle(utils.Style8(workbook));
		        cell03.setCellValue(xzqh+"公安局");//填报时间（当前导出时间）
		        
				Cell cell0=row1.createCell(7);
				cell0.setCellStyle(utils.Style8(workbook));
		        cell0.setCellValue(year+"年"+month+"月");//填报时间（当前导出时间）
		        
		        Cell cell01=row1.createCell(16);
				cell01.setCellStyle(utils.Style8(workbook));
		        cell01.setCellValue("苏鹏琪");////填报人
		        
		        Map<String,String> map2 = new HashMap<>();
				map.put("tjyf", tjyf);
				map.put("xzqh", xzqh);
		        Baojingqingkuang baojingHj = baojingqingkuangDao.findShiHj(map2);
		        //合计1
		        Row row4 = sh.createRow(4);
		        Cell cell04=row4.createCell(3);
				cell04.setCellStyle(utils.Style6(workbook));
				cell04.setCellValue(baojingHj.getHj());
				//合计2
		        Cell cell05=row4.createCell(4);
				cell05.setCellStyle(utils.Style6(workbook));
				cell05.setCellValue(baojingHj.getWffzaj());
				//合计3
		        Cell cell06=row4.createCell(5);
				cell06.setCellStyle(utils.Style6(workbook));
				cell06.setCellValue(baojingHj.getZaaj());
				//合计4
		        Cell cell07=row4.createCell(6);
				cell07.setCellStyle(utils.Style6(workbook));
				cell07.setCellValue(baojingHj.getHzsg());
				//合计5
		        Cell cell08=row4.createCell(7);
				cell08.setCellStyle(utils.Style6(workbook));
				cell08.setCellValue(baojingHj.getJtsg());
				//合计6
		        Cell cell09=row4.createCell(8);
				cell09.setCellStyle(utils.Style6(workbook));
				cell09.setCellValue(baojingHj.getZazhsg());
				//合计7
		        Cell cell010=row4.createCell(9);
				cell010.setCellStyle(utils.Style6(workbook));
				cell010.setCellValue(baojingHj.getZhsg());
				//合计8
		        Cell cell012=row4.createCell(10);
				cell012.setCellStyle(utils.Style6(workbook));
				cell012.setCellValue(baojingHj.getZs());
				//合计9
		        Cell cell013=row4.createCell(11);
				cell013.setCellStyle(utils.Style6(workbook));
				cell013.setCellValue(baojingHj.getJf());
				//合计10
		        Cell cell014=row4.createCell(12);
				cell014.setCellStyle(utils.Style6(workbook));
				cell014.setCellValue(baojingHj.getJtbl());
				//合计11
		        Cell cell015=row4.createCell(13);
				cell015.setCellStyle(utils.Style6(workbook));
				cell015.setCellValue(baojingHj.getGmqz());
				//合计12
		        Cell cell016=row4.createCell(14);
				cell016.setCellStyle(utils.Style6(workbook));
				cell016.setCellValue(baojingHj.getZsxr());
				//合计13
		        Cell cell017=row4.createCell(15);
				cell017.setCellStyle(utils.Style6(workbook));
				cell017.setCellValue(baojingHj.getJwjd());
				//合计14
		        Cell cell018=row4.createCell(16);
				cell018.setCellStyle(utils.Style6(workbook));
				cell018.setCellValue(baojingHj.getQt());
				for(int j=0; j<BaojingReport.size(); j++) {
					if(BaojingReport.get(j).getBjqk().equals("110接警")){
						System.out.println("进来啦");
						Row row5 = sh.createRow(5);
						Cell cell00=row5.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row5.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row5.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row5.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row5.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row5.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row5.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row5.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row5.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row5.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row5.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row5.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row5.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row5.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("执勤巡逻发现")){
						Row row6 = sh.createRow(6);
						Cell cell00=row6.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row6.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row6.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row6.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row6.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row6.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row6.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row6.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row6.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row6.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row6.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row6.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row6.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row6.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("器材报警")){
						Row row7 = sh.createRow(7);
						Cell cell00=row7.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row7.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row7.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row7.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row7.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row7.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row7.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row7.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row7.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row7.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row7.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row7.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row7.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row7.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("口头报警")){
						Row row8 = sh.createRow(8);
						Cell cell00=row8.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row8.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row8.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row8.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row8.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row8.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row8.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row8.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row8.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row8.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row8.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row8.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row8.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row8.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("电话报警")){
						Row row9 = sh.createRow(9);
						Cell cell00=row9.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row9.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row9.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row9.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row9.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row9.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row9.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row9.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row9.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row9.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row9.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row9.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row9.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row9.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("短信微信报警")){
						Row row10 = sh.createRow(10);
						Cell cell00=row10.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getWffzaj());
			            
						Cell cell1=row10.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row10.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row10.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row10.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row10.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row10.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row10.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row10.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row10.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row10.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row10.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row10.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row10.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("举报")){
						Row row11 = sh.createRow(11);
						Cell cell00=row11.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row11.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row11.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row11.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row11.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row11.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row11.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row11.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row11.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row11.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row11.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row11.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row11.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row11.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("扭送现行")){
						Row row12 = sh.createRow(12);
						Cell cell00=row12.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row12.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row12.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row12.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row12.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row12.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row12.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row12.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row12.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row12.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row12.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row12.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row12.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row12.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("投案自首")){
						Row row13 = sh.createRow(13);
						Cell cell00=row13.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row13.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row13.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row13.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row13.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row13.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row13.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row13.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row13.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row13.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row13.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row13.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row13.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row13.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("其他部门移送")){
						Row row14 = sh.createRow(14);
						Cell cell00=row14.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row14.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row14.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row14.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row14.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row14.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row14.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row14.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row14.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row14.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row14.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row14.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row14.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row14.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("其他")){
						Row row15 = sh.createRow(15);
						Cell cell00=row15.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row15.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row15.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row15.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row15.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row15.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row15.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row15.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row15.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row15.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row15.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row15.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row15.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row15.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("出动警力")){
						Row row16 = sh.createRow(16);
						Cell cell00=row16.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row16.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row16.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row16.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row16.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row16.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row16.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row16.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row16.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row16.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row16.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row16.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row16.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row16.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("处置报警(起)")){
						Row row17 = sh.createRow(17);
						Cell cell00=row17.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row17.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row17.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row17.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row17.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row17.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row17.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row17.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row17.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row17.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row17.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row17.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row17.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row17.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("现场抓获违法犯罪人员(人)")){
						Row row18 = sh.createRow(18);
						Cell cell00=row18.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row18.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row18.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row18.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row18.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row18.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row18.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row18.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row18.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row18.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row18.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row18.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row18.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row18.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("逃犯(人)")){
						Row row19 = sh.createRow(19);
						Cell cell00=row19.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row19.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row19.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row19.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row19.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row19.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row19.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row19.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row19.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row19.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row19.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row19.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row19.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row19.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("救助伤员(人)")){
						Row row20 = sh.createRow(20);
						Cell cell00=row20.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row20.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row20.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row20.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row20.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row20.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row20.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row20.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row20.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row20.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row20.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row20.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row20.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row20.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("救助群众(人)")){
						Row row21 = sh.createRow(21);
						Cell cell00=row21.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row21.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row21.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row21.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row21.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row21.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row21.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row21.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row21.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row21.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row21.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row21.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row21.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row21.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("继续盘问(人)")){
						Row row22 = sh.createRow(22);
						Cell cell00=row22.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row22.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row22.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row22.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row22.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row22.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row22.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row22.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row22.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row22.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row22.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row22.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row22.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row22.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("无效报警(起)")){
						Row row23 = sh.createRow(23);
						Cell cell00=row23.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row23.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row23.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row23.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row23.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row23.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row23.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row23.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row23.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row23.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row23.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row23.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row23.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row23.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("死亡(人)")){
						Row row24 = sh.createRow(24);
						Cell cell00=row24.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row24.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row24.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row24.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row24.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row24.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row24.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row24.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row24.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row24.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row24.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row24.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row24.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row24.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
					else if(BaojingReport.get(j).getBjqk().equals("受伤(人)")){
						Row row25 = sh.createRow(25);
						Cell cell00=row25.createCell(3);
			            cell00.setCellStyle(utils.Style6(workbook));
			            cell00.setCellValue(BaojingReport.get(j).getHj());
			            
						Cell cell1=row25.createCell(4);
			            cell1.setCellStyle(utils.Style6(workbook));
			            cell1.setCellValue(BaojingReport.get(j).getWffzaj());
			            
			            Cell cell2=row25.createCell(5);
			            cell2.setCellStyle(utils.Style6(workbook));
			            cell2.setCellValue(BaojingReport.get(j).getZaaj());
			            
			            Cell cell3=row25.createCell(6);
			            cell3.setCellStyle(utils.Style6(workbook));
			            cell3.setCellValue(BaojingReport.get(j).getHzsg());
			            
			            Cell cell4=row25.createCell(7);
			            cell4.setCellStyle(utils.Style6(workbook));
			            cell4.setCellValue(BaojingReport.get(j).getJtsg());
			            
			            Cell cell5=row25.createCell(8);
			            cell5.setCellStyle(utils.Style6(workbook));
			            cell5.setCellValue(BaojingReport.get(j).getZazhsg());
			            
			            Cell cell6=row25.createCell(9);
			            cell6.setCellStyle(utils.Style6(workbook));
			            cell6.setCellValue(BaojingReport.get(j).getZhsg());
			            
			            Cell cell7=row25.createCell(10);
			            cell7.setCellStyle(utils.Style6(workbook));
			            cell7.setCellValue(BaojingReport.get(j).getZs());
			            
			            Cell cell8=row25.createCell(11);
			            cell8.setCellStyle(utils.Style6(workbook));
			            cell8.setCellValue(BaojingReport.get(j).getJf());
			            
			            Cell cell9=row25.createCell(12);
			            cell9.setCellStyle(utils.Style6(workbook));
			            cell9.setCellValue(BaojingReport.get(j).getJtbl());
			            
			            Cell cell10=row25.createCell(13);
			            cell10.setCellStyle(utils.Style6(workbook));
			            cell10.setCellValue(BaojingReport.get(j).getGmqz());
			            
			            Cell cell11=row25.createCell(14);
			            cell11.setCellStyle(utils.Style6(workbook));
			            cell11.setCellValue(BaojingReport.get(j).getZsxr());
			            
			            Cell cell12=row25.createCell(15);
			            cell12.setCellStyle(utils.Style6(workbook));
			            cell12.setCellValue(BaojingReport.get(j).getJwjd());
			            
			            Cell cell13=row25.createCell(16);
			            cell13.setCellStyle(utils.Style6(workbook));
			            cell13.setCellValue(BaojingReport.get(j).getQt());
			        	}
				}
				String year1 =tjyf.substring(0,4);			//2019
				String month2 =tjyf.substring(5,7);         //09
				String title = year1+"年"+month2+"月"+"报警情况统计月报表";
				
				OutputStream out = response.getOutputStream();
//				response.reset();
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
