
package com.toughguy.educationSystem.controller.authority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.toughguy.educationSystem.dto.UserDTO;
import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.model.authority.Role;
import com.toughguy.educationSystem.model.authority.User;
import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.persist.authority.prototype.IOperationDao;
import com.toughguy.educationSystem.persist.authority.prototype.IResourceDao;
import com.toughguy.educationSystem.persist.authority.prototype.IRoleDao;
import com.toughguy.educationSystem.persist.authority.prototype.IUserDao;
import com.toughguy.educationSystem.security.CustomLoginToken;
import com.toughguy.educationSystem.util.JsonUtil;


/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 登录控制器类
 * @author RanJi
 * @date 2017-9-12
 * @since JDK1.7
 * @version 1.0
 */

@Controller
public class LoginController {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IOperationDao operationDao;
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Autowired
	private IRoleDao roleDao;

	private String userPass;
	
	/**
	 *  登录页面（PC端）
	 * @return  登录页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/default/login/login");
		return mv;
	}
	/**
	 *  登录页面（小程序）
	 * @return  登录页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/loginWX", method=RequestMethod.GET)
	public ModelAndView loginPage2(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/default/loginWX/loginWX");
		return mv;
	}
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indexPage(){
		return "/index";
	}
	
	/**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
    
    
	
	
	/**
	 * 登录（PC端）
	 * @param user
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	//@SystemControllerLog(description="登录系统")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(User user, String verityCode, HttpSession session,HttpServletRequest request, String captcha){		
		//-- 产生的验证码获取的方法，若需要认证则自己写验证的逻辑, verityCode为用户输入的验证码，嘿嘿，简单吧
//		String rightCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equals(verityCode.trim())){
			return "{ \"success\" : false ,\"code\":\"您输入的验证码信息不正确,请重新输入\" }";
		}
		//ModelAndView mv = new ModelAndView();
		if(user.getUserName() == null || user.getUserName().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"账户不能为空\" }";
		}else if(user.getUserPass() == null || user.getUserPass().trim() == ""){
			return "{ \"success\" : false ,\"code\":\"密码不能为空\" }";
		}
		//获取Subject实例对象
		//在shiro里面所有的用户的会话信息都会由Shiro来进行控制，那么也就是说只要是与用户有关的一切的处理信息操作都可以通过Shiro取得，
		// 实际上可以取得的信息可以有用户名、主机名称等等，这所有的信息都可以通过Subject接口取得
		Subject subject = SecurityUtils.getSubject();
		 //将用户名和密码封装到继承了UsernamePasswordToken的userToken
		System.out.println(user.getUserPass());
		CustomLoginToken userToken = new CustomLoginToken(user.getUserName(), user.getUserPass(), "ACCOUNT");
		System.out.println("-------------------------"+userToken.getPassword());
		userToken.setRememberMe(false);
        try {
            //认证
            // 传到ModularRealmAuthenticator类中，然后根据ADMIN_LOGIN_TYPE传到AdminShiroRealm的方法进行认证
            subject.login(userToken);
            //Admin存入session
            SecurityUtils.getSubject().getSession().setAttribute("user",(User)subject.getPrincipal());
            User u = userDao.findUserInfoByUserName(user.getUserName());
    		List<Role> roles = roleDao.findByUserId(u.getId());
    		String roleDisplayName = "";
    		for(Role role:roles) {
    			roleDisplayName += role.getDisplayName() + ",";
    		}
    		String newRoleDisplayName = roleDisplayName.substring(0, roleDisplayName.length()-1);
    		UserDTO ut = new UserDTO();
    		List<Operation> list = operationDao.findByUserId(u.getId());
    		String name = "";          //用户拥有的操作名称
    		String ResourceName ="";   
    		for (Operation operation : list) {
    			String permission = operation.getPermission();
    			name += permission+",";
    			int resourceId = operation.getResourceId();
    			String reName = resourceDao.find(resourceId).getResourceName();
    			ResourceName += reName+",";
    		}
    		//去重
    		String [] stringArr= ResourceName.substring(0, ResourceName.length()-1).split(",");;
    		Set set = new HashSet();
    		for (int i = 0; i < stringArr.length; i++) {
    			set.add(stringArr[i]);
    		}
    		stringArr = (String[]) set.toArray(new String[0]);
    		String resourceName = ""; //用户拥有的资源名称
    		for (int i = 0; i < stringArr.length; i++) {
    			resourceName +=stringArr[i]+",";
    		}
            ut.setRoleName(newRoleDisplayName);
    		ut.setPermissions(name.substring(0, name.length()-1));
    		ut.setResourceName(resourceName.substring(0, resourceName.length()-1));
    		ut.setToken(session.getId());
    		BeanUtils.copyProperties(u, ut); //省去set的烦恼，利用beanUtils进行属性copy
    		String userDTO = JsonUtil.objectToJson(ut);
    		//mv.setViewName("redirect:/index.html");
    		return "{ \"success\" : true ,\"user\":"+userDTO+"}";
        } catch (Exception e) {
            //认证失败就会抛出AuthenticationException这个异常，就对异常进行相应的操作，这里的处理是抛出一个自定义异常ResultException
            //到时候我们抛出自定义异常ResultException，用户名或者密码错误
            e.printStackTrace();
        	return "{ \"success\" : false ,\"code\":\"您输入的用户名或密码不正确,请重新输入\" }";
        }
	}
}