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
import com.toughguy.dataDisplay.model.content.DictJJLXDMB;
import com.toughguy.dataDisplay.persist.content.prototype.IDictJJLXDMBDao;
import com.toughguy.dataDisplay.service.content.prototype.IDictJJLXDMBService;
import com.toughguy.dataDisplay.service.impl.GenericServiceImpl;
import com.toughguy.dataDisplay.util.POIUtils;


/**
 * 字典表-接警类型代码表Service实现类
 * @author zmk
 *
 */
@Service
public class DictJJLXDMBServiceImpl extends GenericServiceImpl<DictJJLXDMB, Integer> implements IDictJJLXDMBService{
	
	
	@Autowired
	IDictJJLXDMBDao  dictJJLXDMBDao;
	
	@Override
	public List<DictJJLXDMB> findAll(){
		// TODO Auto-generated method stub
		return dictJJLXDMBDao.findAll();
	}

	
			            
			           
				            
				            

}
