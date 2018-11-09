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
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.toughguy.reportingSystem.util.BackupUtil;
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
//	@RequiresPermissions("information:save")
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
//	@RequiresPermissions("information:edit")
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
//	@RequiresPermissions("information:detele")
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
		try {
			Information i = informationService.find(id);
			Informer ir = informerService.find(i.getInformerId());
//			String str1 = JsonUtil.objectToJson(i);
//			String str2 = JsonUtil.objectToJson(ir);
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("information", i);
			result.put("informer", ir);
			return om.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/getInformation")
//	public List<Information> getInformation(String informerId) {
//		System.out.println(informerId);
//		return informationService.getInformation(informerId);
//	}
	@ResponseBody
	@RequestMapping(value = "/getInformation")
	public List<Information> getInformationByOpenId(String openId) {
		Informer inf = informerService.getInformer(openId);
		List<Information> inft= informationService.getInformation(inf.getId());
		if(inft != null){
			return inft;
		}else{
			return null;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/toExamine")
	public String toExamine(int id,boolean isPass,int assessorId,String feedbackInformation) {
		try {
			Information i = informationService.find(id);
			if(isPass) {
				i.setAssessorId(assessorId);
				i.setFeedbackInformation(feedbackInformation);
				i.setState(1);
				i.setId(id);
				informationService.update(i);
			} else {
				i.setAssessorId(assessorId);
				i.setFeedbackInformation(feedbackInformation);
				i.setState(4);
				i.setId(id);
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
		//String filePath = "D:\\eclipse\\reportingSystem\\upload\\";
		String filePath = "C:\\Users\\Administrator\\git\\reportingSystem\\upload\\barcode";
//		C:/Users/Administrator/git/reportingSystem/upload/barcodeUswx_camera_1535114750894.mp4
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
	

	//小程序的图片上传
	@ResponseBody
	@RequestMapping(value = "/uploadImagefile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入get图片方法！");
 
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/barcode";
        
        String path = BackupUtil.rename("jpg");
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            File file  =  new File(realPath,path);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return path;
    }
	@ResponseBody
	@RequestMapping(value = "/uploadViedofile" ,method = { RequestMethod.POST,RequestMethod.GET})
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    System.out.println("进入get视频方法！");
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //服务器路径需要换
        String realPath = "C:/Users/Administrator/git/reportingSystem/upload/video";
        
        String path = BackupUtil.rename("mp4");
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            File file  =  new File(realPath,path);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return path;
    }
//	@ResponseBody
//	@RequestMapping(value = "/uploadViedofile" ,method = { RequestMethod.POST,RequestMethod.GET})
//	public  String fileUpload(HttpServletRequest request,HttpServletResponse response)throws Exception{
//	    //允许上传的文件最大大小(100M,单位为byte)
//	    int maxSize = 1024*1024*100;
//	    response.addHeader("Access-Control-Allow-Origin", "*");
//	    System.out.println("进入get视频方法！");
//	  
//	         MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
//	         MultipartFile multipartFile =  req.getFile("file");
//	         
//	         if (multipartFile.getSize() > maxSize)
//	         {
//	        	 
//	             JOptionPane.showMessageDialog(null, "文件大小超过限制！应小于" + maxSize
//	                                                 / 1024 / 1024 + "M");
//	             return "文件大小超过限制！应小于" + maxSize;
//	         }
//	
//	         String realPath = "C:/Users/Administrator/git/reportingSystem/upload/video";
//	         
//	         String path = BackupUtil.rename("mp4");
//	         try {
//	             File dir = new File(path);
//	             if (!dir.exists()) {
//	                 dir.mkdir();
//	             }
//	             
//	             File file  =  new File(realPath,path);
//	             multipartFile.transferTo(file);
//	         } catch (IOException e) {
//	             e.printStackTrace();
//	         } catch (IllegalStateException e) {
//	             e.printStackTrace();
//	         }
//	         return path;
//	     }
	    
	


}
