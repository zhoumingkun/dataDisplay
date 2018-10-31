package com.toughguy.reportingSystem.controller.business;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.persist.business.prototype.IFeedbackInformationDao;
import com.toughguy.reportingSystem.service.business.prototype.IFeedbackInformationService;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.business.prototype.IInformerService;
import com.toughguy.reportingSystem.util.FileUploadUtil;
import com.toughguy.reportingSystem.util.JsonUtil;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;

@Controller
@RequestMapping(value = "/information")
public class InformationController {
	@Autowired
	private IInformationService informationService;
	
	@Autowired
	private IInformerService informerService;
	
	@Autowired
	private IFeedbackInformationService feedbackInformerService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	@RequiresPermissions("information:save")
	public String saveInformation(Information information) {
		try {
			informationService.save(information);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	@RequiresPermissions("information:edit")
	public String editInformation(Information information) {
		try {
			informationService.update(information);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	@RequiresPermissions("information:detele")
	public String deleteInformation(int id) {
		try {
			informationService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
//	@RequiresPermissions("information:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Information> pg = informationService.findPaginated(map);
			
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", pg.getData());
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findNum")
//	@RequiresPermissions("information:findNum")
	public InformationDTO findNum() {
		try {
			InformationDTO informationDTO = new InformationDTO();
			int sum = informationService.findNum(0);   //总数
			int invalidNumber = informationService.findNum(4);   //无效案件
			int validNumber = informationService.findNum(1);     //已接案件
			int endNumber = informationService.findNum(3);       //已结案件
			informationDTO.setSum(sum);
			informationDTO.setInvalidNumber(invalidNumber);
			informationDTO.setValidNumber(validNumber);
			informationDTO.setEndNumber(endNumber);
			return informationDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findSumAndValid")
//	@RequiresPermissions("information:findSumAndValid")
	public List<InformationDTO> findSumAndValid() {
		try {
			List<Integer> is = informationService.findValidNumber(); //首页图表每日已接案
			List<InformationDTO> informationDTOs  = informationService.findSum(); //首页图表每日数量汇总
			for(int i=0;i<informationDTOs.size();i++) {
				informationDTOs.get(i).setValidNumber(is.get(i));
			}
			return informationDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/get")
	public String get(int id) {
		Information i = informationService.find(id);
		Informer ir = informerService.find(i.getInformerId());
		String str1 = JsonUtil.objectToJson(i);
		String str2 = JsonUtil.objectToJson(ir);
		return str1 + str2;
	}
	@ResponseBody
	@RequestMapping(value = "/toExamine")
	public String toExamine(int id,boolean isPass,int assessorId,int feedbackInformationId) {
		try {
			Information i = informationService.find(id);
			if(isPass) {
				i.setAssessorId(assessorId);
				i.setFeedbackInformationId(feedbackInformationId);
				i.setState(1);
				informationService.update(i);
			} else {
				i.setAssessorId(assessorId);
				i.setFeedbackInformationId(feedbackInformationId);
				i.setState(4);
				informationService.update(i);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/upload")
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,int id, int state) {
		String fileName = file.getOriginalFilename();
		String filePath = "D:\\eclipse\\reportingSystem\\upload\\";
		try {
			FileUploadUtil.uploadFile(file.getBytes(), filePath, fileName);
			Information information = informationService.find(id);
			information.setState(state);
			if(state == 2) {
				information.setValidFile(filePath);
			} else if(state == 3) {
				information.setInvestigationFile(filePath);
			}
			informationService.update(information);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "upload success";
	}
	
}
