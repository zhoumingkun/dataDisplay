package com.toughguy.educationSystem.controller.content;
import java.util.Date;
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
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.Activity;
import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeOrganization;
import com.toughguy.educationSystem.model.content.Zhengcefagui;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.service.content.prototype.IActivityService;
import com.toughguy.educationSystem.service.content.prototype.IGuizhangzhiduService;
import com.toughguy.educationSystem.service.content.prototype.ISingleOptionService;
import com.toughguy.educationSystem.service.content.prototype.ISizhengjiansheService;
import com.toughguy.educationSystem.service.content.prototype.ITestService;
import com.toughguy.educationSystem.service.content.prototype.ITopicService;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeDepartmentService;
import com.toughguy.educationSystem.service.content.prototype.IXiaoyuanhuangyeOrganizationService;
import com.toughguy.educationSystem.service.content.prototype.IZhengcefaguiService;
import com.toughguy.educationSystem.util.DateUtil;

@Controller
@RequestMapping(value = "/weixinContent")
public class WeixinContentController {

    @Autowired
	private IActivityService activityService;
    @Autowired
	private ISizhengjiansheService sizhengjiansheService;
    @Autowired
	private IZhengcefaguiService zhengcefaguiService;
    @Autowired
	private IGuizhangzhiduService guizhangzhiduService;
    @Autowired
	private IXiaoyuanhuangyeOrganizationService xiaoyuanhuangyeOrganizationService;
	@Autowired
	private IXiaoyuanhuangyeDepartmentService xiaoyuanhuangyeDepartmentService;
	@Autowired
	private ITestService testService;
	@Autowired
	private ITopicService topicService;
	@Autowired
	private ISingleOptionService singleOptionService;
	@Autowired
	private IAccountService accountService;
    
    @ResponseBody
	@RequestMapping(value = "/findNew")
	//@RequiresPermissions("activity:findNew")
	public Activity findNew() {
		return activityService.findNew();
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataSizheng")
	//@RequiresPermissions("sizhengjianshe:data")
	public String dataSizheng(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Sizhengjianshe> pg = sizhengjiansheService.findPaginated(map);
			
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
	 * 小程序思政建设访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSizheng")
//	@RequiresPermissions("sizhengjianshe:getById")
	public Sizhengjianshe getSizheng(int id) {
		Sizhengjianshe szjs =  sizhengjiansheService.find(id);
		szjs.setHits(szjs.getHits() + 1);
		return szjs;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataActivity")
	//@RequiresPermissions("activity:data")
	public String dataActivity(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Activity> pg = activityService.findPaginated(map);
			
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
	 * 小程序活动动态文档访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivity")
//	@RequiresPermissions("activity:getById")
	public Activity getActivity(int id) {
		Activity activity =  activityService.find(id);
		activity.setHits(activity.getHits() + 1);
		return activity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataZhengce")
	//@RequiresPermissions("zhengcefagui:data")
	public String dataZhengce(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Zhengcefagui> pg = zhengcefaguiService.findPaginated(map);
			
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
	 * 小程序政策法规文档访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getZhengce")
//	@RequiresPermissions("zhengcefagui:getById")
	public Zhengcefagui getZhengce(int id) {
		Zhengcefagui zhengcefagui =  zhengcefaguiService.find(id);
		zhengcefagui.setHits(zhengcefagui.getHits() + 1);
		return zhengcefagui;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataGuizhang")
	//@RequiresPermissions("guizhangzhidu:data")
	public String dataGuizhang(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Guizhangzhidu> pg = guizhangzhiduService.findPaginated(map);
			
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
	 * 小程序规章制度文档访问使用（统计浏览量）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getGuizhang")
//	@RequiresPermissions("guizhangzhidu:getById")
	public Guizhangzhidu getGuizhang(int id) {
		Guizhangzhidu guizhangzhidu =  guizhangzhiduService.find(id);
		guizhangzhidu.setHits(guizhangzhidu.getHits() + 1);
		return guizhangzhidu;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataXiaoyuan")
	//@RequiresPermissions("xiaoyuanhuangye:data")
	public String dataXiaoyuan(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<XiaoyuanhuangyeOrganization> pg = xiaoyuanhuangyeOrganizationService.findPaginated(map);
			
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
	@RequestMapping(value = "/findAllDepartment")
	//@RequiresPermissions("xiaoyuanhuangye:findAllDepartment")
	public List<XiaoyuanhuangyeDepartment> findAllDepartment(int id) {
		try {
			return xiaoyuanhuangyeDepartmentService.findAllDepartment(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataTest")
	//@RequiresPermissions("test:data")
	public String dataTest(String params,HttpSession session) {
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
	
	/**
	 * 小程序获取某道题的第一页详情(统计浏览量)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTest")
	//@RequiresPermissions("test:get")
	public Test getTest(int id) {
		Test test =  testService.find(id);
		test.setHits(test.getHits() + 1);
		return test;
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

	@ResponseBody
	@RequestMapping(value = "/dataAccount")
	//@RequiresPermissions("account:data")
	public String dataAccount(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Account> pg = accountService.findPaginated(map);
			
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
	 * 通过openId获取某个用户的积分
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getIntegralByOpenId")
	//@RequiresPermissions("account:getIntegralByOpenId")
	public String getIntegralByOpenId(String openId) {
		Account a = accountService.findByOpenId(openId);
		return "{ \"integral\":" + a.getIntegral() + "}";
	}
	
	/**
	 * 签到
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signIn")
	//@RequiresPermissions("account:signIn")
	public String signIn(String openId) {
		System.out.println(openId);
		Account a = accountService.findByOpenId(openId);
		if(a.getSignDate() == null || "".equals(a.getSignDate())) {
			a.setIntegral(a.getIntegral() + 2);
			a.setSignDate(DateUtil.now());
			accountService.update(a);
			return "{ \"integral\":" + a.getIntegral() + "}";
		} else {
			Date signDate = null;
			signDate = DateUtil.now();
			boolean timeSignIsToday = DateUtil.isToday(signDate);
			if(timeSignIsToday) {
				return "{ \"success\": false}";
			} else {
				a.setIntegral(a.getIntegral() + 2);
				Date newDate = new Date();
				a.setSignDate(newDate);
				return "{ \"integral\":" + a.getIntegral() + "}";
			}
		}
	}

	
}
