package com.toughguy.educationSystem.controller.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.security.CustomLoginToken;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.util.DateUtil;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IAccountResultService accountResultService;
	
	@ResponseBody	
	@RequestMapping(value = "/save")
	//@RequiresPermissions("account:save")
	public String saveAccount(Account account,String openId) {
		try {
			boolean isOpenId = false;
			List<Account> accounts = accountService.findAll();
			for(Account a:accounts) {
				if(a.getOpenId() == null || "".equals(a.getOpenId())) {
					
				} else {
					if(a.getOpenId().equals(openId)) {
						//已注册过
						isOpenId = true;
					}
				}
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
		if(account.getAccount() == null || account.getAccount().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"账户不能为空\" }";
		}else if(account.getPassword() == null || account.getPassword().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"密码不能为空\" }";
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
        	return "{ \"success\" : false ,\"code\":\"您输入的用户名或密码不正确,请重新输入\" }";
        }
	}
		
	@ResponseBody
	@RequestMapping(value = "/reset")
//	@RequiresPermissions("account:reset")
	public String resetPwd(int id) {
		try {
			Account account = accountService.find(id);
			account.setPassword(new DefaultPasswordService().encryptPassword("123456"));
			accountService.update(account);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("account:edit")
	public String editAccount(Account newAccount) {
		try {
			Account account = accountService.find(newAccount.getId());
			account.setSex(newAccount.getSex());
			account.setPassword(new DefaultPasswordService().encryptPassword(newAccount.getPassword()));
			account.setPhoneNum(newAccount.getPhoneNum());
			account.setIntegral(newAccount.getIntegral());
			accountService.update(account);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	/**
	 * 添加积分
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addIntegral")
	@RequiresPermissions("account:addIntegral")
	public String addIntegral(int id,int addIntegral) {
		try {
			Account a = accountService.find(id);
			int newIntegral = a.getIntegral() + addIntegral;
			a.setIntegral(newIntegral);
			accountService.update(a);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/deleteAccount")
	//@RequiresPermissions("account:deleteAccount")
	public String deleteAccount(int id) {
		try {
			accountService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 将学生测评改为(安全)
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	@RequiresPermissions("account:delete")
	public String deleteAccountResult(int id) {
		try {
			List<AccountResult> ars = accountResultService.findByAccountId(id);
			for(AccountResult ar:ars) {
				ar.setRank(2);
				accountResultService.update(ar);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	/**
	 * 将学生测评改为(安全)
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteByTestId")
	@RequiresPermissions("account:delete")
	public String deleteByTestId(int id,int testId) {
		try {
			List<AccountResult> ars = accountResultService.findByAccountId(id);
			for(AccountResult ar:ars) {
				if(ar.getTestId() == testId) {
					ar.setRank(2);
					accountResultService.update(ar);
				}
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/data")
	//@RequiresPermissions("account:data")
	public String data(String params,HttpSession session) {
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
	@ResponseBody
	@RequestMapping(value = "/dataRiskAssessment")
	//@RequiresPermissions("account:dataRiskAssessment")
	public String dataRiskAssessment(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Account> pg = accountService.findPaginated(map);
			//为了不把密码传到前台，而创建的新list
			List<Account> newas = new ArrayList<>();
			List<Account> as = pg.getData();
			System.out.println(as.size());
			for(Account a:as) {
				int riskAssessment = accountResultService.findRiskAssessment(a.getId());
				if(riskAssessment == 0) {
				} else {
					a.setRiskAssessment(riskAssessment);
					a.setPassword("");
					newas.add(a);
				}
			}
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", newas);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	/**
	 * 查询某题的问题学生
	 * @param params
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dataRiskByTask")
	//@RequiresPermissions("account:dataRiskByTask")
	public String dataRiskByTask(String params,HttpSession session) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Account> pg = accountService.findAllByRisk(map);
			//为了不把密码传到前台，而创建的新list
			List<Account> newas = new ArrayList<>();
			List<Account> as = pg.getData();
			for(Account a:as) {
				int riskAssessment = accountResultService.findRiskAssessment(a.getId());
				a.setRiskAssessment(riskAssessment);
				a.setPassword("");
				newas.add(a);
			}
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", newas);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/findAll")
	//@RequiresPermissions("account:findAll")
	public List<Account> findAll() {
		return accountService.findAll();
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
	 * 获取某个用户的积分
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getIntegral")
	//@RequiresPermissions("account:getIntegral")
	public String getIntegral(int id) {
		Account a = accountService.find(id);
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