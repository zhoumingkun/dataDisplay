package com.toughguy.dataDisplay.controller.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.dataDisplay.model.authority.Operation;
import com.toughguy.dataDisplay.model.authority.Role;
import com.toughguy.dataDisplay.pagination.PagerModel;
import com.toughguy.dataDisplay.service.authority.prototype.IAuthorityService;
import com.toughguy.dataDisplay.service.authority.prototype.IRoleService;
import com.toughguy.dataDisplay.util.PinyinUtil;

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
 * 角色控制器类
 * @author FengJie
 * @date 2017-9-29
 * @since JDK1.7
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAuthorityService authService;
		
	@RequestMapping(value = "/list")
	public String listRole() {
		return "default/authority/role/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/get")
	public Role get(int id) {
		Role role = roleService.find(id);
		return role;
		
	}
	
	@RequestMapping(value = "/listAll")
	//@SystemControllerLog(description="权限管理-角色全部列表")
	@ResponseBody
//	@RequiresPermissions("role:listAll")
	public List<Role> listAllRole(HttpSession session) {
		List <Role> roleList = roleService.findRoleTree();
		return roleList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOperation")
	//@SystemControllerLog(description="权限管理-角色操作列表")
	public List<Operation> getOperationByRoleId(int roleId) {
		
		return authService.findOperationsByRoleId(roleId);
	}
	
	
	//@SystemControllerLog(description="权限管理-角色列表")
	@ResponseBody
	@RequestMapping(value = "/data")
//	@RequiresPermissions("role:list")
	public String data(String params) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Role> pg = roleService.findPaginated(map);
			
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
	
	
	@RequestMapping(value = "/add")
	//@SystemControllerLog(description="权限管理-添加角色跳转")
	public String addRole() {
		return "default/authority/role/add";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save")
//	@RequiresPermissions("role:save")
	//@SystemControllerLog(description="权限管理-添加角色")
	public String saveRole(Role newRole) {
		try {
			String displayName = newRole.getDisplayName();
			String roleName = PinyinUtil.converterToSpell(displayName).split(",")[0]; //转为拼音 多音取第一个
			List<Role> list = roleService.findByName(roleName);
			if(list.size() > 0){
				return "角色名重复";
			}else{
				newRole.setRoleName(roleName);
				roleService.save(newRole);
				return "{ \"success\" : true }";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	//@SystemControllerLog(description="权限管理-批量添加角色")
	@RequestMapping(value = "/adds")
	public String addsRoles() {
		return "default/authority/role/adds";
	}
	
	//@SystemControllerLog(description="权限管理-查看角色")
	@RequestMapping(value = "/view/{id}")
//	@RequiresPermissions("role:view")
	public String viewRole(@PathVariable int id, HttpSession session) {
		
		Role role = roleService.find(id);
		session.setAttribute("role", role);
		return "default/authority/role/view";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/edit")
//	@RequiresPermissions("role:edit")
	//@SystemControllerLog(description="权限管理-修改角色")
	public String edit(Role newRole,HttpSession session) {
		try {
			Role role = roleService.find(newRole.getId());
			String displayName = newRole.getDisplayName();
			String roleName = PinyinUtil.converterToSpell(displayName).split(",")[0]; //转为拼音 多音取第一个
			role.setDisplayName(displayName);
			role.setRoleName(roleName);
			role.setRoleExtendPId(newRole.getRoleExtendPId());
			role.setRoleMaxNum(newRole.getRoleMaxNum());
			role.setRemarks(newRole.getRemarks());
			role.setRoleRelyId(newRole.getRoleRelyId());
			roleService.update(role);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/auth")
//	@RequiresPermissions("role:auth")
	//@SystemControllerLog(description="权限管理-给角色分配资源")
	public String authRole(int roleId, String operationIds) {
		try{
			String[] str = operationIds.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for(String s :str){
				list.add(Integer.parseInt(s));
			}
			authService.authRole(roleId, list);
			return "{ \"success\" : true }";
		}catch(Exception e){
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete")
//	@RequiresPermissions("role:delete")
	//@SystemControllerLog(description="权限管理-删除角色")
	public String deleteRole(int id) {
		try {
			roleService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAll")
//	@RequiresPermissions("role:deleteAll")
	//@SystemControllerLog(description="权限管理-删除多个角色")
	public String deleteAllRole(String role_ids) {
		try {
			String[] array  = role_ids.split(",");
			List <Integer> arrays = new ArrayList<Integer>();
			for(String s: array){
				arrays.add(Integer.parseInt(s));
			};
			roleService.deleteByIDS(arrays);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findBydisplayName")
	//@RequiresPermissions("role:findBydisplayName")
	//@SystemControllerLog(description="权限管理-根据角色中文名称查是否重复")
	public String findBydisplayName(String displayName) {
		List<Role> list = roleService.findBydisplayName(displayName);
		if(list.size() > 0){
			return "{ \"success\" : false }";
		}else{
			return "{ \"success\" : true }";
		}
	}
}
