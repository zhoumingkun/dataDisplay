package com.toughguy.reportingSystem.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.reportingSystem.model.business.FeedbackInformation;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.business.prototype.IFeedbackInformationService;

@Controller
@RequestMapping(value = "/feedbackInformation")
public class FeedbackInformationController {
	@Autowired
	private IFeedbackInformationService feedbackInformationService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	public String saveFeedbackInformation(FeedbackInformation feedbackInformation) {
		try {
			feedbackInformationService.save(feedbackInformation);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public String editFeedbackInformation(FeedbackInformation feedbackInformation) {
		try {
			System.out.println(feedbackInformation);
			FeedbackInformation f = feedbackInformationService.findByType(feedbackInformation.getType());
			if(f == null) {
				feedbackInformationService.update(feedbackInformation);
				return "{ \"success\" : true }";
			} else {
				return "{ \"success\" : false }";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{  \"msg\" : \"操作失败\"  }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String deleteFeedbackInformation(int id) {
		try {
			feedbackInformationService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<FeedbackInformation> pg = feedbackInformationService.findPaginated(map);
			
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
	
	/**
	 * 反馈信息避免重复原因
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findByTypeAndSave")
	public String findByType(FeedbackInformation feedbackInformation) {
		try {
			FeedbackInformation f = feedbackInformationService.findByType(feedbackInformation.getType());
			if(f == null) {
				feedbackInformationService.save(feedbackInformation);
				return "{\"success\" : true}";
			} else {
				return "{ \"success\" : false }";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"msg\" : \"操作失败\" }";
		}
	}
}
