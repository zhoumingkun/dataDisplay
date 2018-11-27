package com.toughguy.educationSystem.persist.authority.prototype;

import java.util.List;

import com.toughguy.educationSystem.dto.OperationDTO;
import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.persist.prototype.IGenericDao;

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
 * IOperationDao接口类
 * @author FengJie
 * @date 2017-9-13
 * @since JDK1.7
 * @version 1.0
 */

public interface IOperationDao extends IGenericDao<Operation, Integer> {
	
	/**
	 * 根据资源id删除操作集
	 * @param resourceId 资源id
	 */
	public void deleteAllByResourceId(int resourceId);
	
	/**
	 * 根据资源ID查找操作
	 * @param id 资源id
	 */
	public List<Operation> findById(int id);
	/**
	 * 根据角色ID查找操作
	 * @param id 资源id
	 */
	public List<Operation> findByRoleId(int roleId);
	/*
	 * 根据用户ID查找操作
	 */
	public List<Operation> findByUserId(int userId);
	/**
	 * 根据依赖ID查找操作
	 * @param operationRId 依赖id
	 */
	public Operation findOperation(int operationRId);
	/**
	 * 根据操作ID删除角色操作对应关系表
	 * @param operationRId 依赖id
	 */
	public void deleteRoleAndOperation(int id);
}
