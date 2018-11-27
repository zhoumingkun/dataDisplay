package com.toughguy.educationSystem.service.authority.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.model.authority.Resource;
import com.toughguy.educationSystem.model.authority.Role;
import com.toughguy.educationSystem.persist.authority.prototype.IOperationDao;
import com.toughguy.educationSystem.service.authority.prototype.IOperationService;
import com.toughguy.educationSystem.service.authority.prototype.IResourceService;
import com.toughguy.educationSystem.service.authority.prototype.IRoleService;
import com.toughguy.educationSystem.service.impl.GenericServiceImpl;
@Service
public class OperationServiceImpl extends GenericServiceImpl<Operation, Integer> implements IOperationService {
	
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IOperationDao operationDao;
	@Autowired
	private IRoleService roleService;
	
	
	private List<Operation> operation = new ArrayList<Operation>(); //保存操作集合
	private List<Resource> resource = new ArrayList<Resource>(); //保存资源集合

	@Override
	public List<Resource> findResourceTree(){
		List<Resource> resourceList = resourceService.findAll();
		List<Operation> opera = findAll();
		for(Operation o : opera){
			if(o.getOperationRId()==-1){
				operation.add(o);
				for(Resource r : resourceList){
					if(r.getId()==o.getResourceId()){
						r.getOperationList().add(o);
					}
				}	
			}
			for(Operation o1 : opera){
				if(o1.getOperationRId()==o.getId()){
					o.getOperationList().add(o1);
				}
			}
		}
		return resourceList;
	}

	@Override
	public void deleteAllByResourceId(int resourceId) {
		((IOperationDao)dao).deleteAllByResourceId(resourceId);
	}

	@Override
	public List<Operation> findAllOperationsByRoleId(int roleId) {
		// 获取当前roleid 的 所有操作 ， 并且存入临时存储列表中
		List<Operation> operationListAll = operationDao.findByRoleId(roleId);
		// 查找父角色
		// 	递归调用当前方法 ， 返回结果
		List<Role> relyRoles = roleService.findRelyRole(roleId);
		if(relyRoles.size()>0) {
			for(Role r: relyRoles) {
				List<Operation> relyOperations = findAllOperationsByRoleId(r.getId());
				if(relyOperations.size() > 0) {
					operationListAll.addAll(relyOperations);
				}
			}
		}
		// 对 临时列表和  递归调用结果进行  数组合并 返回
		return operationListAll;
	}
}
