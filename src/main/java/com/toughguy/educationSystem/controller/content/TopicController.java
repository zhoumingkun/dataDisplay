package com.toughguy.educationSystem.controller.content;

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
import com.toughguy.educationSystem.dto.TopicAndSingleOptionDTO;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.content.prototype.ITopicService;

@Controller
@RequestMapping(value = "/topic")
public class TopicController {
	@Autowired
	private ITopicService topicService;
	@Autowired
	private ISingleOptionService singleOptionService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("topic:save")
	public String saveActivity(Topic topic) {
		try {
			topicService.save(topic);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("topic:edit")
	public String editTopic(Topic topic) {
		try {
			topicService.update(topic);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("topic:detele")
	public String deleteTopic(int id) {
		try {
			topicService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("topic:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Topic> pg = topicService.findPaginated(map);
			
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
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("topic:findAll")
	public List<Topic> findAll() {
		return topicService.findAll();
	}
	
	/**
	 * 测试题与选项获取
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findTopicAndSingleOption")
	//@RequiresPermissions("topic:findTopicAndSingleOption")
	public TopicAndSingleOptionDTO findTopicAndSingleOption(int testId) {
		TopicAndSingleOptionDTO tas = new TopicAndSingleOptionDTO();
		List<Topic> ts = topicService.findByTestId(testId);
		System.out.println(ts.size());
		//单题
		if(ts.size()>0 && ts.size()<2) {
			for(Topic t:ts) {
				tas.setTopic(t.getTopic());
				tas.setSingleOption(singleOptionService.findByTopicId(t.getId()));
			}
		} else {
			//分值
		}
		return tas;
	}
}
