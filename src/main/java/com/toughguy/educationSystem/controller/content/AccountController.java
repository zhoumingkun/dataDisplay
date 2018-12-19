package com.toughguy.educationSystem.controller.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.dto.UserDTO;
import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.model.authority.Role;
import com.toughguy.educationSystem.model.authority.User;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;
import com.toughguy.educationSystem.util.DateUtil;
import com.toughguy.educationSystem.util.JsonUtil;

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
	public String saveAccount(Account account) {
		try {
			accountService.save(account);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@RequestMapping(value="/loginWX",method=RequestMethod.POST)
	@ResponseBody
	public String login(Account account, HttpServletRequest request){		
		
	    try{
	    	Subject currentAccount = SecurityUtils.getSubject();
	    	UsernamePasswordToken token = new UsernamePasswordToken(account.getAccount(),account.getPassword());
	    	currentAccount.login(token);
	    } catch ( UnknownAccountException e ) {
	    	return "{ \"success\" : false ,\"code\":\"您输入的用户名或密码不正确,请重新输入\" }";
        } catch ( IncorrectCredentialsException e ) {
        	return "{ \"success\" : false ,\"code\":\"您输入的用户名或密码不正确,请重新输入\" }";
        }
	    return "{ \"success\" : true }";
		
		
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
	//@RequiresPermissions("account:detele")
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
	//@RequiresPermissions("account:detele")
	public String deleteAccountResult(int id) {
		try {
			List<AccountResult> ars = accountResultService.findByAccountId(id);
			for(AccountResult ar:ars) {
				ar.setRank(2);
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
	//@RequiresPermissions("account:data")
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
	//@RequiresPermissions("account:data")
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
	 * 获取某个用户的积分
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getIntegral")
	public String getIntegral(int id) {
		Account a = accountService.find(id);
		return "{ \"integral\":" + a.getIntegral() + "}";
	}
	/**
	 * 签到
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signIn")
	public String signIn(int id) {
		System.out.println(id);
		Account a = accountService.find(id);
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
