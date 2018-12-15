package com.toughguy.educationSystem.controller.content;

import java.awt.geom.Area;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.dto.TopicAndScoreOptionDTO;
import com.toughguy.educationSystem.model.content.ScoreOption;
import com.toughguy.educationSystem.model.content.ScoreRank;
import com.toughguy.educationSystem.model.content.ScoreResult;
import com.toughguy.educationSystem.model.content.SingleOption;
import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IScoreOptionService;
import com.toughguy.educationSystem.service.content.prototype.IScoreRankService;
import com.toughguy.educationSystem.service.content.prototype.IScoreResultService;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.content.prototype.ITestService;
import com.toughguy.educationSystem.service.content.prototype.ITopicService;
import com.toughguy.educationSystem.util.GetIpAndMacUtil;
import com.toughguy.educationSystem.util.JsonUtil;
import com.toughguy.educationSystem.util.MapUtil;
import com.toughguy.educationSystem.util.UploadUtil;


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
	@Autowired
	private IScoreResultService scoreResultService;
	@Autowired
	private IScoreRankService scoreRankService;
	
	@ResponseBody
	@RequestMapping(value = "/uploadPic")
	//@RequiresPermissions("safety:upload")
	public String uploadPicture(MultipartFile pictureFile){
		if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
			try {
			 String path = UploadUtil.uploadPicture(pictureFile);
			 return "{ \"success\" : true ,\"msg\" :\""+ path +"\"}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{ \"success\" : false ,\"msg\" : \"上传失败\"}";
			}
		}else{
			return "{ \"success\" : false , \"msg\" : \"请上传正确图片格式的图片\"}";
		}	
	}
	/**
	 * 添加单题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/saveSingleTopic")
	public String saveSingleTopic(String test,String topic,String singleOptions) {
		Test test1 = new Test();
		Topic topic1 = new Topic();
//		SingleOption singleOption = new SingleOption();
		List<SingleOption> singleOptionList = new ArrayList<SingleOption>();
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(test)) {
				// 参数处理
				map = om.readValue(test, new TypeReference<Map<String, Object>>() {});
				test1 = (Test) MapUtil.mapToBean(map, Test.class);
			}
			if (!StringUtils.isEmpty(topic)) {
				// 参数处理
				map = om.readValue(topic, new TypeReference<Map<String, Object>>() {});
				topic1 = (Topic) MapUtil.mapToBean(map,Topic.class);
			}
			if (!StringUtils.isEmpty(singleOptions)) {
				singleOptionList = JsonUtil.jsonToList(singleOptions, SingleOption.class);
			}
			//题保存
			testService.save(test1);
			//题目保存
			topic1.setTestId(test1.getId());
			topicService.save(topic1);
			//选项（结果）保存
			for(SingleOption s:singleOptionList) {
				s.setTopicId(topic1.getId());
				singleOptionService.save(s);
			}
			return "{ \"success\" : true }";
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
	public String saveScoreTopic(String test,String topics,String scoreOptions,String scoreRanks,String scoreResult) {
		Test test1 = new Test();
		List<Topic> topicList = new ArrayList<Topic>();
		List<ScoreOption> scoreOptionList = new ArrayList<ScoreOption>();
		List<ScoreRank> scoreRankList = new ArrayList<ScoreRank>();
		ScoreResult scoreResult1 = new ScoreResult();
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(test)) {
				// 参数处理
				map = om.readValue(test, new TypeReference<Map<String, Object>>() {});
				test1 = (Test) MapUtil.mapToBean(map, Test.class);
			}
			if (!StringUtils.isEmpty(topics)) {
				topicList = JsonUtil.jsonToList(topics, Topic.class);
			}
			if (!StringUtils.isEmpty(scoreOptions)) {
				scoreOptionList = JsonUtil.jsonToList(scoreOptions, ScoreOption.class);
			}
			if (!StringUtils.isEmpty(scoreRanks)) {
				scoreRankList = JsonUtil.jsonToList(scoreRanks, ScoreRank.class);
			}
			if (!StringUtils.isEmpty(scoreResult)) {
				// 参数处理
				map = om.readValue(scoreResult, new TypeReference<Map<String, Object>>() {});
				scoreResult1 = (ScoreResult) MapUtil.mapToBean(map, ScoreResult.class);
			}
			//图片
			testService.save(test1);
			//题目保存
			for(Topic t:topicList) {
				t.setTestId(test1.getId());
				topicService.save(t);
				//选项保存
				for(ScoreOption so:scoreOptionList) {
					so.setTopicId(t.getId());
					scoreOptionService.save(so);
				}
				//结果保存
				scoreResult1.setTestId(test1.getId());
				scoreResultService.save(scoreResult1);
				//评分保存
				for(ScoreRank sr:scoreRankList) {
					sr.setTestId(test1.getId());
					scoreRankService.save(sr);
				}
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 获取单题题目+选项+结果
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSingleTopic")
	public String getSingleTopic(int testId,HttpServletRequest request) {
		try {
			System.out.println(GetIpAndMacUtil.getIp(request));
			System.out.println(GetIpAndMacUtil.getMacAddress(GetIpAndMacUtil.getIp(request)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("错啦");
		}
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		Test test = testService.find(testId);
		List<Topic> topics = topicService.findByTestId(testId);
		List<SingleOption> singleOptions = singleOptionService.findByTopicId(topics.get(0).getId());
		result.put("test", test);
		result.put("topics", topics.get(0));
		result.put("singleOptions", singleOptions);
		try {
			return om.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取分值题题目+选项+结果
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getScoreTopic")
	public String getScoreTopic(int testId) {
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		Test test = testService.find(testId);
		List<Topic> topics = topicService.findByTestId(testId);
		TopicAndScoreOptionDTO topicAndScoreOptionDTO = new TopicAndScoreOptionDTO();
		for(Topic t:topics) {
			topicAndScoreOptionDTO.setTopicId(t.getId());
			topicAndScoreOptionDTO.setTopic(t.getTopic());
			topicAndScoreOptionDTO.setScoreOptions(scoreOptionService.findByTopicId(t.getId()));
		}
		topicAndScoreOptionDTO.setScoreResult(scoreResultService.findByTestId(test.getId()));
		topicAndScoreOptionDTO.setScoreRank(scoreRankService.findByTestId(testId));
		result.put("test", test);
		result.put("topicAndScoreOption", topicAndScoreOptionDTO);
		try {
			return om.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
	public String editSingleTopic(String tests,String topics,String singleOptions) {
		Test test = new Test();
		Topic topic = new Topic();
//		SingleOption singleOption = new SingleOption();
		List<SingleOption> singleOptionList = new ArrayList<SingleOption>();
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(tests)) {
				// 参数处理
				map = om.readValue(tests, new TypeReference<Map<String, Object>>() {});
				
				test = (Test) MapUtil.mapToBean(map, Test.class);
			}
			if (!StringUtils.isEmpty(topics)) {
				// 参数处理
				map = om.readValue(topics, new TypeReference<Map<String, Object>>() {});
				topic = (Topic) MapUtil.mapToBean(map,Topic.class);
			}
			if (!StringUtils.isEmpty(singleOptions)) {
				singleOptionList = JsonUtil.jsonToList(singleOptions, SingleOption.class);
			}
			//题保存
			testService.update(test);
			//题目保存
			topic.setTestId(test.getId());
			topicService.update(topic);
			//选项（结果）保存
			for(SingleOption s:singleOptionList) {
				s.setTopicId(topic.getId());
				singleOptionService.update(s);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	/**
	 * 编辑分值题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/editScoreTopic")
	public String editScoreTopic(String test,String topics,String scoreOptions,String scoreRanks,String scoreResult) {
		Test test1 = new Test();
		List<Topic> topicList = new ArrayList<Topic>();
		List<ScoreOption> scoreOptionList = new ArrayList<ScoreOption>();
		List<ScoreRank> scoreRankList = new ArrayList<ScoreRank>();
		ScoreResult scoreResult1 = new ScoreResult();
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(test)) {
				// 参数处理
				map = om.readValue(test, new TypeReference<Map<String, Object>>() {});
				test1 = (Test) MapUtil.mapToBean(map, Test.class);
			}
			if (!StringUtils.isEmpty(topics)) {
				topicList = JsonUtil.jsonToList(topics, Topic.class);
			}
			if (!StringUtils.isEmpty(scoreOptions)) {
				scoreOptionList = JsonUtil.jsonToList(scoreOptions, ScoreOption.class);
			}
			if (!StringUtils.isEmpty(scoreRanks)) {
				scoreRankList = JsonUtil.jsonToList(scoreRanks, ScoreRank.class);
			}
			if (!StringUtils.isEmpty(scoreResult)) {
				// 参数处理
				map = om.readValue(scoreResult, new TypeReference<Map<String, Object>>() {});
				scoreResult1 = (ScoreResult) MapUtil.mapToBean(map, ScoreResult.class);
			}
			//图片
			testService.update(test1);
			//题目保存
			for(Topic t:topicList) {
				t.setTestId(test1.getId());
				topicService.update(t);
				//选项保存
				for(ScoreOption so:scoreOptionList) {
					so.setTopicId(t.getId());
					scoreOptionService.update(so);
				}
				//结果保存
				scoreResult1.setTestId(test1.getId());
				scoreResultService.update(scoreResult1);
				//评分保存
				for(ScoreRank sr:scoreRankList) {
					sr.setTestId(test1.getId());
					scoreRankService.update(sr);
				}
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
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
