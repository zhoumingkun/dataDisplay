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
import com.toughguy.dataDisplay.model.content.RecJJLXTJB;
import com.toughguy.dataDisplay.persist.content.prototype.IRecJJLXTJBDao;
import com.toughguy.dataDisplay.service.content.prototype.IRecJJLXTJBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 统计表表-报警类型统计表 Service实现类
 * @author zmk
 *
 */
@Service
public class RecJJLXTJBServiceImpl extends GenericServiceImpl<RecJJLXTJB, Integer> implements IRecJJLXTJBService{
	
	
	@Autowired
	IRecJJLXTJBDao  recJJLXTJBDao;
	
	@Override
	public List<RecJJLXTJB> findAll(){
		// TODO Auto-generated method stub
		return recJJLXTJBDao.findAll();
	}

	
			            
			           
				            
				            

}
