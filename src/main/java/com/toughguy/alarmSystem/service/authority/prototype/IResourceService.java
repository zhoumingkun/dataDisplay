package com.toughguy.alarmSystem.service.authority.prototype;

import java.util.List;

import com.toughguy.alarmSystem.dto.TreeDTO;
import com.toughguy.alarmSystem.model.authority.Resource;
import com.toughguy.alarmSystem.service.prototype.IGenericService;

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
 * Authority模块中的IUserService接口类
 * @author FengJie
 * @date 2017-9-15
 * @since JDK1.7
 * @version 1.0
 */
public interface IResourceService extends IGenericService<Resource, Integer> {

	// 用户操作方法
		/**
		 * 根据资源id查询全部的资源-操作
		 * @param resourceId 资源id
		 * @return 操作id集合
		 */
		public List<Integer> findROsByResourceId(int resourceId);
		
		/**
		 * 查询全部资源，树形结构
		 * @param roleId 
		 * @return 资源
		 */
		 public List<TreeDTO> findResourceTree(int roleId);
		 
		 
		 public List<Resource> findByresourceName(String resourceName);

		 /**
		 * 查看资源
		 * @param resourceId
		 * @return Resource
		 */
		public Resource checkResource(int resourceId);

	}
