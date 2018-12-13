package com.toughguy.educationSystem.controller.content;

import java.util.ArrayList;
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
import com.toughguy.educationSystem.util.JsonUtil;
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
	/**
	 * 添加单题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody	
	@RequestMapping(value = "/saveSingleTopic")
	public String saveSingleTopic(String params,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					ObjectMapper om = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					if (!StringUtils.isEmpty(params)) {
						// 参数处理
						map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
					}
					Test test = (Test) map.get("test");
					Topic topic = (Topic) map.get("topic");
					List<SingleOption> singleOptions = (List<SingleOption>) map.get("singleOption");
					//题保存
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.save(test);
					//题目保存
					Test ts = testService.findByTitle(test.getTitle());
					topic.setTestId(ts.getId());
					topicService.save(topic);
					//选项（结果）保存
					Topic topics = topicService.findByTopic(topic.getTopic());
					for(SingleOption s:singleOptions) {
						s.setTopicId(topics.getId());
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
	@SuppressWarnings("unchecked")
	@ResponseBody	
	@RequestMapping(value = "/saveScoreTopic")
	public String saveScoreTopic(String params,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					ObjectMapper om = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					if (!StringUtils.isEmpty(params)) {
						// 参数处理
						map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
					}
					Test test = (Test) map.get("test");
					List<Topic> topics = (List<Topic>) map.get("topics");
					List<ScoreOption> scoreOptions = (List<ScoreOption>) map.get("scoreOptions");
					List<ScoreRank> scoreRanks = (List<ScoreRank>) map.get("scoreRanks");
					ScoreResult scoreResult = (ScoreResult) map.get("scoreResults");
					//图片
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.save(test);
					//查询当前保存的题
					Test ts = testService.findByTitle(test.getTitle());
					//题目保存
					for(Topic t:topics) {
						t.setTestId(ts.getId());
						topicService.save(t);
						//查询当前保存的题目
						Topic topic2 = topicService.findByTopic(t.getTopic());
						//选项保存
						for(ScoreOption so:scoreOptions) {
							so.setTopicId(topic2.getId());
							scoreOptionService.save(so);
						}
						//结果保存
						scoreResult.setTestId(ts.getId());
						scoreResultService.save(scoreResult);
						//评分保存
						for(ScoreRank sr:scoreRanks) {
							sr.setTestId(ts.getId());
							scoreRankService.save(sr);
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
	 * 获取单题题目+选项+结果
	 * @param testId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSingleTopic")
	public String getSingleTopic(int testId) {
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
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/editSingleTopic")
	//@RequiresPermissions("test:edit")
	public String editSingleTopic(String params,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					ObjectMapper om = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					if (!StringUtils.isEmpty(params)) {
						// 参数处理
						map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
					}
					Test test = (Test) map.get("test");
					Topic topic = (Topic) map.get("topic");
					List<SingleOption> singleOptions = (List<SingleOption>) map.get("singleOption");
					//题编辑
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.update(test);
					//题目编辑
					topic.setTestId(test.getId());
					topicService.update(topic);
					//选项（结果）编辑
					for(SingleOption s:singleOptions) {
						s.setTopicId(topic.getId());
						singleOptionService.update(s);
					}
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
	
	/**
	 * 编辑分值题测试测试题表+题目表+选项表
	 * @param test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody	
	@RequestMapping(value = "/editScoreTopic")
	public String editScoreTopic(String params,MultipartFile pictureFile) {
		try {
			if(UploadUtil.isPicture(pictureFile.getOriginalFilename())){
				try {
					ObjectMapper om = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					if (!StringUtils.isEmpty(params)) {
						// 参数处理
						map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
					}
					Test test = (Test) map.get("test");
					List<Topic> topics = (List<Topic>) map.get("topic");
					List<ScoreOption> scoreOptions = (List<ScoreOption>) map.get("scoreOption");
					List<ScoreRank> scoreRanks = (List<ScoreRank>) map.get("scoreRank");
					ScoreResult scoreResult = (ScoreResult) map.get("scoreResult");
					//图片
					String path = UploadUtil.uploadPicture(pictureFile);
					test.setImage(path);
					testService.update(test);
					//题目保存
					for(Topic t:topics) {
						t.setTestId(test.getId());
						topicService.update(t);
						//查询当前保存的题目
						Topic topic2 = topicService.findByTopic(t.getTopic());
						//选项保存
						for(ScoreOption so:scoreOptions) {
							so.setTopicId(topic2.getId());
							scoreOptionService.update(so);
						}
						//结果保存
						scoreResult.setTestId(test.getId());
						scoreResultService.update(scoreResult);
						//评分保存
						for(ScoreRank sr:scoreRanks) {
							sr.setTestId(test.getId());
							scoreRankService.update(sr);
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
