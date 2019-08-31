package com.toughguy.alarmSystem.service.authority.prototype;

import java.util.List;

import com.toughguy.alarmSystem.model.authority.Operation;
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
public interface IOperationService extends IGenericService<Operation, Integer> {
	
	/**
	 * 查询所有资源及操作
	 * @return 操作对象集合
	 */
	public List<Resource> findResourceTree();
	

	/**
	 * 根据资源id删除操作集
	 * @param resourceId 资源id
	 */
	public void deleteAllByResourceId(int resourceId);
	
	/**
	 * 根据角色id或依赖id查询所有操作集
	 * @param roleId 角色id或角色依赖id
	 * @return 操作集
	 */
	public List<Operation> findAllOperationsByRoleId(int roleId);

}
