package com.toughguy.dataDisplay.service.content.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.toughguy.dataDisplay.model.content.DictXZQHB;
import com.toughguy.dataDisplay.model.content.RecGrade;
import com.toughguy.dataDisplay.persist.content.prototype.IDictXZQHBDao;
import com.toughguy.dataDisplay.persist.content.prototype.IRecGradeDao;
import com.toughguy.dataDisplay.service.content.prototype.IDictXZQHBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 字典表-行政区划表Service实现类
 * @author zmk
 *
 */
@Service
public class DictXZQHBServiceImpl extends GenericServiceImpl<DictXZQHB, Integer> implements IDictXZQHBService{
	
	
	@Autowired
	private IDictXZQHBDao  dictXZQHBDao;
	
	@Autowired
	private IRecGradeDao recGradeDao;
	
	
	@Override
	public List<DictXZQHB> findAll(){
		// TODO Auto-generated method stub
		return dictXZQHBDao.findAll();
	}

	@Override
	public String save(List<DictXZQHB> list) {
		// TODO Auto-generated method stub
		try {
			for(int i =0;i<list.size();i++) {
				dictXZQHBDao.save(list.get(i));
			}
			return "{ \"success\" : true }";
		}catch (Exception e) {
			// TODO: handle exception
			return "{ \"success\" : false }";
		}
	}
	
	@Override
	public void updateXZQH(DictXZQHB xzqh) {
		// TODO Auto-generated method stub
		((IDictXZQHBDao)dao).updateXZQH(xzqh);
	}

	@Override
	public Map<String, Object> findMapProportion() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		List<DictXZQHB> list = dictXZQHBDao.findAll();			//查询全部行政区划
		List<RecGrade> slist = recGradeDao.selectAll();			//查询省各级占比
		DecimalFormat df = new DecimalFormat("0.00");
		for(int i=0;i<list.size();i++) {
			Map<String,Object> hmap = new HashMap<>();
			for(int a=0;a<slist.size();a++) {
				int jjslsx = list.get(i).getJjslsx();
				double d =Double.valueOf(df.format((float)Integer.parseInt(slist.get(a).getProportion())/100));
				hmap.put(slist.get(a).getGradedm(), df.format(jjslsx/d));
			}
			map.put(list.get(i).getXzqhmc(), hmap);
		}
		return map;
	}

	
			            
			           
				            
				            

}
