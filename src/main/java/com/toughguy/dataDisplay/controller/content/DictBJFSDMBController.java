package com.toughguy.dataDisplay.controller.content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.dataDisplay.model.content.DictBJFSDMB;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.content.prototype.IDictBJFSDMBService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




@RestController
@RequestMapping(value = "/dictBJFSDMB")
public class DictBJFSDMBController {
	@Autowired
	private IDictBJFSDMBService dictBJFSDMBService;
	
	
	

	
	
	@ResponseBody
	@RequestMapping(value = "/getAll")
//	@RequiresPermissions("dictXZQHB:getById")
	public String getAll(String json) {
		 String Path="C:\\json\\" + json+ ".json";
		 BufferedReader reader = null;
	     String laststr = "";
	     try {
	          FileInputStream fileInputStream = new FileInputStream(Path);
	          InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
	          reader = new BufferedReader(inputStreamReader);
	          String tempString = null;
	          while ((tempString = reader.readLine()) != null) {
	              laststr += tempString;
	          }
	          reader.close();
	     } catch (IOException e) {
	         e.printStackTrace();
	        } finally {
	           if (reader != null) {
	               try {
	                 reader.close();
	             } catch (IOException e) {
	                 e.printStackTrace();
	                }
	           }
	       }
	       return laststr;
	}
	

}
