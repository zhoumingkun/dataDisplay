package com.toughguy.dataDisplay.controller.authority;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.dataDisplay.dto.TreeDTO;
import com.toughguy.dataDisplay.model.authority.Role;
import com.toughguy.dataDisplay.model.authority.User;
import com.toughguy.dataDisplay.service.authority.prototype.IAuthorityService;
import com.toughguy.dataDisplay.service.authority.prototype.IUserService;
import com.toughguy.dataDisplay.util.JsonUtil;

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
 * Authority模块中的UserController控制器类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthorityService authService;
	
	
	//@SystemControllerPermission("user:add")
	@RequestMapping(value = "/add")
	//@SystemControllerLog(description="权限管理-添加用户跳转")
	public String addUser() {
		return "default/authority/user/add";
	}
	
	@ResponseBody	
	@RequestMapping(value = "/save")
//	@RequiresPermissions("user:save")
	//@SystemControllerLog(description="权限管理-添加用户")
	public String saveUser(User user) {
		try {
			userService.save(user);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/reset")
//	@RequiresPermissions("user:reset")
	//@SystemControllerLog(description="权限管理-添加用户")
	public String resetPwd(int id) {
		try {
			User user = userService.find(id);
			user.setUserPass(new DefaultPasswordService().encryptPassword("123456"));
			userService.update(user);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	//@SystemControllerLog(description="权限管理-用户列表")
	@RequestMapping(value = "/list")
	public String listUser() {
		return "default/authority/user/list";
	}
	
	@RequestMapping(value = "/adds")
	//@SystemControllerLog(description="权限管理-批量添加用户")
	public String AddsUser() {
		return "default/authority/user/adds";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	//@SystemControllerLog(description="权限管理-更新用户")
//	@RequiresPermissions("user:edit")
	public String editUser(User newUser) {
		try {
			User user = userService.find(newUser.getId());
			user.setUserName(newUser.getUserName());
			user.setUserPass(new DefaultPasswordService().encryptPassword(newUser.getUserPass()));
			user.setUnitName(newUser.getUnitName());
			userService.update(user);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/auth")
//	@RequiresPermissions("user:auth")
	//@SystemControllerLog(description="权限管理-给用户分配角色")
	public String authUser(String roleIds, int userId) {
		try{
			String[] str = roleIds.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for(String s :str){
				list.add(Integer.parseInt(s));
			}
			authService.authUser(userId, list);
			return "{ \"success\" : true }";
		}catch(Exception e){
			return "{ \"success\" : false }";
		}
	}
	
	//@SystemControllerLog(description="权限管理-获取用户角色")
	@ResponseBody
	@RequestMapping(value = "/getRole")
	public String getRoleByUser(Integer id) {
		List<Role> roleList = authService.findRolesByUserId(id);
		return JsonUtil.objectToJson(roleList);
	}
	
	//@SystemControllerLog(description="权限管理-获取用户角色")
	@ResponseBody
	@RequestMapping(value = "/getRoles")
	public List<TreeDTO> findRoleByUser(Integer id) {
			List<TreeDTO> list = authService.findRoleByUser(id);
			return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
//	@RequiresPermissions("user:detele")
	public String deleteUser(int id) {
		try {
			userService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAll")
//	@RequiresPermissions("user:deleteAll")
	//@SystemControllerLog(description="权限管理-删除多个用户")
	public String deteteAllUser(String user_ids) {
		try {
			String[] array  = user_ids.split(",");
			List <Integer> arrays = new ArrayList<Integer>();
			for(String s: array){
				arrays.add(Integer.parseInt(s));
			};
			userService.deleteByIDS(arrays);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/get/{id}")
//	@RequiresPermissions("user:view")
	public String get(@PathVariable int id) {
		try {
			ObjectMapper om = new ObjectMapper();
			User obj = userService.find(id);
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	@ResponseBody
	//@SystemControllerLog(description="权限管理-用户列表")
	@RequestMapping(value = "/data")
	//@RequiresPermissions("user:list")
	public String data(String params,HttpSession session) {
		return authService.findAllUserInduleRoles(params);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findByuserName")
//	@RequiresPermissions("user:findByuserName")
	//@SystemControllerLog(description="权限管理-根据用户名称查是否重复")
	public String findByuserName(String userName) {
		List<User> list = userService.findByuserName(userName);
		if(list.size() > 0){
			return "{ \"success\" : false }";
		}else{
			return "{ \"success\" : true }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findUnitNameById")
//	@RequiresPermissions("user:detele")
	public String findUnitNameById(User user) {
		String unitName=userService.findUnitNameById(user);
		return  unitName;
		}
	}


