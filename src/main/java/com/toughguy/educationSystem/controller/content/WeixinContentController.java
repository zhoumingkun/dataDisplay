package com.toughguy.educationSystem.controller.content;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.dto.TopicAndSingleOptionDTO;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.model.content.Activity;
import com.toughguy.educationSystem.model.content.Guizhangzhidu;
import com.toughguy.educationSystem.model.content.Notice;
import com.toughguy.educationSystem.model.content.Sizhengjianshe;
import com.toughguy.educationSystem.model.content.Test;
import com.toughguy.educationSystem.model.content.Topic;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeDepartment;
import com.toughguy.educationSystem.model.content.XiaoyuanhuangyeOrganization;
import com.toughguy.educationSystem.model.content.Zhengcefagui;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.security.CustomLoginToken;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.service.content.prototype.IActivityService;
import com.toughguy.educationSystem.service.content.prototype.IGuizhangzhiduService;
import com.toughguy.educationSystem.service.content.prototype.INoticeService;
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
	@Autowired
	private IAccountResultService accountResultService;
	@Autowired
	private INoticeService noticeService;
	
	@ResponseBody	
	@RequestMapping(value = "/saveAccountResult")
	//@RequiresPermissions("accountResult:save")
	public String saveAccountResult(AccountResult accountResult) {
		try {
			accountResultService.save(accountResult);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("account:save")
	public String saveAccount(Account account,String openId) {
		try {
			boolean isOpenId = false;
			boolean isAccount = false;
			List<Account> accounts = accountService.findAll();
			for(Account a:accounts) {
				if(account.getAccount().equals(a.getAccount())) {
					//用户名重复
					isAccount = true;
				}
				if(a.getOpenId() == null || "".equals(a.getOpenId())) {
					
				} else {
					if(a.getOpenId().equals(openId)) {
						//已注册过
						isOpenId = true;
					}
				}
			}
			if(isAccount) {
				return "{ \"success\" : false,\"msg\": \"该账号已被注册\"}";
			}
			if(isOpenId) {
				return "{ \"success\" : false,\"msg\": \"该微信已注册，请直接登录\"}";
			} else {
				accountService.save(account);
				return "{ \"success\" : true ,\"msg\": \"注册成功\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@RequestMapping(value="/loginWX",method=RequestMethod.POST)
	//@RequiresPermissions("account:loginWX")
	@ResponseBody
	public String login(Account account, HttpServletRequest request){	
		System.out.println(account.getAccount());
		Account account1 = accountService.findByAccount(account.getAccount());
		if(account.getAccount() == null || account.getAccount().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"账号不能为空\" }";
		}else if(account.getPassword() == null || account.getPassword().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"密码不能为空\" }";
		}
		if(account1 == null) {
			return "{ \"success\" : false ,\"code\":\"您输入的账号或密码不正确,请重新输入\" }";
		} else {
			if(!account.getOpenId().equals(account1.getOpenId())) {
				return "{ \"success\" : false ,\"code\":\"该账号与微信不匹配，请重新输入\" }";
			}
		}
		//获取Subject实例对象
		//在shiro里面所有的用户的会话信息都会由Shiro来进行控制，那么也就是说只要是与用户有关的一切的处理信息操作都可以通过Shiro取得，
		// 实际上可以取得的信息可以有用户名、主机名称等等，这所有的信息都可以通过Subject接口取得
		Subject subject = SecurityUtils.getSubject();
		 //将用户名和密码封装到继承了UsernamePasswordToken的userToken
		CustomLoginToken userToken = new CustomLoginToken(account.getAccount(), account.getPassword(), "ACCOUNT");
        userToken.setRememberMe(false);
        
        try {
            //认证
        	System.out.println("认证密码"+ account.getPassword());
            // 传到ModularRealmAuthenticator类中，然后根据ADMIN_LOGIN_TYPE传到AdminShiroRealm的方法进行认证
            subject.login(userToken);
            //Admin存入session
            SecurityUtils.getSubject().getSession().setAttribute("account",(Account)subject.getPrincipal());
            return "{ \"success\" : true ,\"code\":\"登录成功\" }";
        } catch (Exception e) {
            //认证失败就会抛出AuthenticationException这个异常，就对异常进行相应的操作，这里的处理是抛出一个自定义异常ResultException
            //到时候我们抛出自定义异常ResultException，用户名或者密码错误
            e.printStackTrace();
        	return "{ \"success\" : false ,\"code\":\"您输入的账号或密码不正确,请重新输入\" }";
        }
	}
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
		sizhengjiansheService.update(szjs);
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
		activityService.update(activity);
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
		zhengcefaguiService.update(zhengcefagui);
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
		guizhangzhiduService.update(guizhangzhidu);
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
		testService.update(test);
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
			//获取今天的日期
			boolean timeSignIsToday = DateUtil.isToday(a.getSignDate());
			if(timeSignIsToday) {
				return "{ \"success\": false}";
			} else {
				a.setIntegral(a.getIntegral() + 2);
				a.setSignDate(DateUtil.now());
				accountService.update(a);
				return "{ \"integral\":" + a.getIntegral() + "}";
			}
		}
	}
	
	/**
	 * 校园公告
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dataNotice")
	//@RequiresPermissions("notice:data")
	public String data(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Notice> pg = noticeService.findPaginated(map);
			
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
	 * 小程序获校园公告(统计浏览量)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getNotice")
	//@RequiresPermissions("notice:get")
	public Notice getNotice(int id) {
		Notice notice =  noticeService.find(id);
		notice.setHits(notice.getHits() + 1);
		noticeService.update(notice);
		return notice;
	}

	
}
