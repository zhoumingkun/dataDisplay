package com.toughguy.educationSystem.controller.content;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.toughguy.educationSystem.dto.TopicAndScoreOptionDTO;
import com.toughguy.educationSystem.dto.TopicAndSingleOptionDTO;
import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.model.content.Xiaoyuanhuangye;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.persist.content.prototype.IAccountResultDao;
import com.toughguy.educationSystem.persist.content.prototype.ISingleOptionDao;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IScoreOptionService;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.content.prototype.ITestService;
import com.toughguy.educationSystem.service.content.prototype.ITopicService;
import com.toughguy.educationSystem.util.JsonUtil;
import com.toughguy.educationSystem.util.UploadUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private ITestService testService;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private ISingleOptionService singleOptionService;
	@Autowired
	private IScoreOptionService scoreOptionService;
	@Autowired
	private IAccountResultService accountResultService;
	
	/**
	 * 添加单题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/saveSingleTopic")
	public String saveSingleTopic(Test test,Topic topic,String singleOption,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.save(test);
					List<Test> ts = testService.findByTitle(test.getTitle());
					topic.setTestId(ts.get(0).getId());
					topicService.save(topic);
					List<Topic> topics = topicService.findByTopic(topic.getTopic());
					//JSONString转list对象
					List<SingleOption> singleOptions = new ArrayList<SingleOption>();
					singleOptions = JsonUtil.jsonToList(singleOption, SingleOption.class);
					for(SingleOption s:singleOptions) {
						s.setTopicId(topics.get(0).getId());
						singleOptionService.save(s);
					}
					return "{ \"success\" : true }";
				} catch (Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false ,\"msg\" : \"上传失败\"}";
				}
			}else{
				return "{ \"success\" : false , \"msg\" : \"请上传正确图片格式的图片\"}";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 添加分值题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/saveScoreTopic")
	public String saveScoreTopic(Test test,String topicAndScoreOption,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.save(test);
					List<Test> ts = testService.findByTitle(test.getTitle());
					//JSONString转list对象
					List<TopicAndScoreOptionDTO> topics1 = new ArrayList<TopicAndScoreOptionDTO>();
					topics1 = JsonUtil.jsonToList(topicAndScoreOption, TopicAndScoreOptionDTO.class);
					for(TopicAndScoreOptionDTO tDTO:topics1) {
						Topic t = new Topic();
						t.setTestId(ts.get(0).getId());
						t.setTopic(tDTO.getTopic());
						topicService.save(t);
						List<Topic> topics2 = topicService.findByTopic(t.getTopic());
						for(ScoreOption so:tDTO.getScoreOptions()) {
							so.setTopicId(topics2.get(0).getId());
							scoreOptionService.save(so);
						}
					}
					return "{ \"success\" : true }";
				} catch (Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false ,\"msg\" : \"上传失败\"}";
				}
			}else{
				return "{ \"success\" : false , \"msg\" : \"请上传正确图片格式的图片\"}";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 单题编辑
	 * @param test
	 * @param topic
	 * @param singleOption
	 * @param pictureFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editSingleTopic")
	//@RequiresPermissions("test:edit")
	public String editSingleTopic(String test,String topic,String singleOption,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					String path = UploadUtil.uploadPicture(pictureFile);
					//json字符串转java对象修改题
					Test testObject = JsonUtil.jsonToPojo(test,Test.class);
					testObject.setImage(path);
					testService.update(testObject);
					//json字符串转java对象修改题目
					Topic topicObject = JsonUtil.jsonToPojo(topic,Topic.class);
					topicObject.setTestId(testObject.getId());
					topicService.update(topicObject);
					//json字符串转java对象修改选项
					SingleOption singleOptionObject = JsonUtil.jsonToPojo(singleOption,SingleOption.class);
					singleOptionObject.setTopicId(topicObject.getId());
					singleOptionService.update(singleOptionObject);
					return "{ \"success\" : true }";
				} catch (Exception e) {
					e.printStackTrace();
					return "{ \"success\" : false ,\"msg\" : \"上传失败\"}";
				}
			} else{
				return "{ \"success\" : false , \"msg\" : \"请上传正确图片格式的图片\"}";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	//@RequiresPermissions("activity:detele")
	public String deletetest(int id) {
		try {
			testService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("test:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Test> pg = testService.findPaginated(map);
			
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
	@RequestMapping(value = "/dataStatistical")
	public String dataStatistical(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Test> pg = testService.findPaginated(map);
			
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			List<Test> ts = pg.getData();
			Map<String, Object> params1 = new HashMap<String, Object>();
			int testerPassSum = accountResultService.findTesterPassSum(params1);
			int testerFailureSum = accountResultService.findTesterFailureSum(params1);
			for(Test t:ts) {
				t.setTesterPassSum(testerPassSum);
				t.setTesterFailureSum(testerFailureSum);
			}
			result.put("total", pg.getTotal());
			result.put("rows", ts);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	
	/**
	 * 根据部门名称查询
	 * */
	@ResponseBody
	@RequestMapping(value = "/findByType")
	// @RequiresPermissions("test:findByType")
	public List<Test> findByType(String type) {
		return testService.findByType(type);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("test:findAll")
	public List<Test> findAll() {
		return testService.findAll();
	}
	/**
	 * 获取某道题的第一页详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get")
	public Test get(int id) {
		return testService.find(id);
	}
	
	
}
