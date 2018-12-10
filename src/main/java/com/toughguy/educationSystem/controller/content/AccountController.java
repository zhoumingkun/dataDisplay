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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.model.content.AccountResult;
import com.toughguy.educationSystem.pagination.PagerModel;
import com.toughguy.educationSystem.service.content.prototype.IAccountResultService;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;

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
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@RequiresPermissions("account:edit")
	public String editAccount(Account account) {
		try {
			accountService.update(account);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
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
	public String deleteAccount(int id) {
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
	
}
