package com.toughguy.educationSystem.controller.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toughguy.educationSystem.dto.TreeDTO;
import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.model.authority.Resource;
import com.toughguy.educationSystem.service.authority.prototype.IAuthorityService;
import com.toughguy.educationSystem.service.authority.prototype.IOperationService;
import com.toughguy.educationSystem.service.authority.prototype.IResourceService;
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
 * 资源控制器类
 * @author yaoyao
 * @date 2017-9-29
 * @since JDK1.7
 * @version 1.0
 */

@Controller
@RequestMapping(value="/resource")
public class ResourceController {
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IAuthorityService authService;
	@Autowired
	private IOperationService operationService;
		
	@RequestMapping(value = "/list")
	//@SystemControllerLog(description="权限管理-资源列表")
	public String listResource() {
		return "default/authority/resources/list";
	}

	@RequestMapping(value = "/add")
	//@SystemControllerLog(description="权限管理-添加资源跳转")
	public String addResources() {
		return "default/authority/resources/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	@RequiresPermissions("resource:save")
	//@SystemControllerLog(description="权限管理-添加资源")
	public String saveResources(Resource resource,String params) {
		try {
			authService.saveResourceAndOperation(resource, params);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	@RequiresPermissions("resource:edit")
	//@SystemControllerLog(description="权限管理-编辑资源")
	public String editResource(Resource newResource,String params) {
		try {
			Resource resource = resourceService.find(newResource.getId());
			resource.setResourceName(newResource.getResourceName());
			resource.setResourceType(newResource.getResourceType());
			resource.setResourcePId(newResource.getResourcePId());
			authService.updateResourceAndOperation(resource,params);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}	
	
	//@SystemControllerLog(description="权限管理-资源列表")
	@ResponseBody
	@RequestMapping(value = "/data")
	@RequiresPermissions("resource:list")
	public String data(String params,HttpSession session) {
		return authService.findAllResourceInduleOperation(params);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/listAll")
//	@RequiresPermissions("resource:listAll")
	public List<TreeDTO> findResource(int roleId) {
		List<TreeDTO> treeDtoList = resourceService.findResourceTree(roleId);
		return treeDtoList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/get/resourceAndOperation")
//	@RequiresPermissions("resource:resourceAndOperation")
	public String findResourceAndOperation() {
		List<Resource> resourceList = resourceService.findAll();
		List<Operation> operationList = operationService.findAll();
		Map <String,Object> map = new HashMap<String,Object>();
		map.put("resource", resourceList);
		map.put("operation", operationList);
		return JsonUtil.toJsonByProperties(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
//	@RequiresPermissions("resource:delete")
	public String deleteResource(int operationId) {
		try {
			if(operationId != 0){
				authService.deleteResource(operationId);
			}
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	
	//@SystemControllerLog(description="权限管理-删除多个资源")
	@ResponseBody
	@RequestMapping(value = "/deleteAll")
//	@RequiresPermissions("resource:deleteAll")
	public String deteteAllResource(String resource_ids) {
		try {
			String[] array  = resource_ids.split(",");
			List <Integer> arrays = new ArrayList<Integer>();
			for(String s: array){
				arrays.add(Integer.parseInt(s));
			};
			resourceService.deleteByIDS(arrays);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/findByresourceName")
//	@RequiresPermissions("resource:findByresourceName")
	//@SystemControllerLog(description="权限管理-根据资源名称查是否重复")
	public String findByresourceName(String resourceName) {

			List<Resource> list = resourceService.findByresourceName(resourceName);
			if(list.size() > 0){
				return "{ \"success\" : false }";
			}else{
				return "{ \"success\" : true }";
			}
		}
	
	

	/**
	 * 查看资源
	 * @param resourceId
	 * @return Resource
	 */
	@ResponseBody
	@RequestMapping(value = "/checkResource")
//	@RequiresPermissions("resource:check")
	public Resource checkResource(int resourceId) {
		Resource resource = resourceService.checkResource(resourceId);
		return resource;
	}
	
}