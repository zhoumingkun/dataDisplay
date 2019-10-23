package com.toughguy.dataDisplay.service.content.impl;

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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.toughguy.dataDisplay.model.content.RecJQTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecJQTJBDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecJQTJBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 统计表表-警情统计表  Service实现类
 * @author zmk
 *
 */
@Service
public class RecJQTJBServiceImpl extends GenericServiceImpl<RecJQTJB, Integer> implements IRecJQTJBService{
	
	
	@Autowired
	IRecJQTJBDao  recJQTJBDao;
	
	@Override
	public List<RecJQTJB> findJQNum(String tjTime) {
		// TODO Auto-generated method stub
		return recJQTJBDao.findJQNum(tjTime);
	}

	@Override
	public List<RecJQTJB> findJQSevenDayShen(String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map<String ,String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return recJQTJBDao.findJQSevenDayShen(map);
	}
	
	@Override
	public List<RecJQTJB> findXZQHNum(String tjTime) {
		// TODO Auto-generated method stub
		return recJQTJBDao.findXZQHNum(tjTime);
	}

	
			            
			           
				            
				            

}
