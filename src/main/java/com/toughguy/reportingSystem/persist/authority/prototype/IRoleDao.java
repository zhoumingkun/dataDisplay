package com.toughguy.reportingSystem.persist.authority.prototype;

import java.util.List;

import com.toughguy.reportingSystem.model.authority.Operation;
import com.toughguy.reportingSystem.model.authority.Role;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

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
 * IRoleDao接口类
 * @author LiJianBo
 * @date 2017-9-13
 * @since JDK1.7
 * @version 1.0
 */
public interface IRoleDao extends IGenericDao<Role, Integer>{
	
	/**
	 * 存储  角色-操作的对应
	 * @param roleId 角色id
	 * @param operationId 操作id
	 */
	public void saveRoleAndOperationRelation(int roleId, int operationId);

	/**
	 * 删除 角色-操作的对应
	 * @param roleId 角色id
	 * @param operationId 操作id
	 */
	public void deleteRoleAndOperationRelation(int roleId, int operationId);

	/**
	 * 删除 某角色的全部角色-操作的对应
	 * @param roleId 操作id
	 */
	public void deleteRoleAndOperationsRelationByRoleId(int roleId);

	/**
	 * 根据角色id查询全部的 角色-操作对应
	 * @param roelId 角色id
	 * @return 操作id集合
	 */
	public List<Integer> findRoleAndOperationsRelationByRoleId(int roleId);
	
	/**
	 * 根据角色id查询全部的 角色-操作对应
	 * @author fengjie
	 * @param roelId 角色id
	 * @return 操作对象集合
	 */
	public List<Operation> findOperationByRoleId(int roleId);
	
	/**
	 * 查询角色包含角色目前数量
	 * @author fengjie
	 * @return 角色对象集合
	 */
	public List<Role> findAllCount();
	/**
	 * 根据角色名查询角色
	 * @param roleName 角色名
	 */
	public List<Role> findByName(String roleName);
	
	/**
	 * 查询某个角色根据角色中文名称
	 * @param displayName 角色中文名称
	 */
	
	public List<Role> findBydisplayName(String displayName);

	/**
	 * 根据角色ID查询下属角色
	 * @param roleName 角色名
	 */
	public List<Role> findRelyRole(int id);
	/**
	 * 根据角色依赖ID查询对应的角色 
	 */
	public Role findRelyId(int roleRelyId);
	/**
	 * 根据用户Id查询对应的角色 
	 */
	public List<Role> findByUserId(int id);
}
