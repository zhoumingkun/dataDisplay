package com.toughguy.alarmSystem.service.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toughguy.alarmSystem.dto.OperationDTO;
import com.toughguy.alarmSystem.dto.TreeDTO;
import com.toughguy.alarmSystem.model.authority.Operation;
import com.toughguy.alarmSystem.model.authority.Resource;
import com.toughguy.alarmSystem.model.authority.Role;
import com.toughguy.alarmSystem.model.authority.User;
import com.toughguy.alarmSystem.pagination.PagerModel;
import com.toughguy.alarmSystem.persist.authority.prototype.IOperationDao;
import com.toughguy.alarmSystem.persist.authority.prototype.IRoleDao;
import com.toughguy.alarmSystem.service.authority.prototype.IAuthorityService;
import com.toughguy.alarmSystem.service.authority.prototype.IOperationService;
import com.toughguy.alarmSystem.service.authority.prototype.IResourceService;
import com.toughguy.alarmSystem.service.authority.prototype.IRoleService;
import com.toughguy.alarmSystem.service.authority.prototype.IUserService;
import com.toughguy.alarmSystem.util.JsonUtil;
import com.toughguy.alarmSystem.util.PinyinUtil;


@Service
public class AuthorityServiceImpl implements IAuthorityService{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IOperationService operationService;
	@Autowired
	private IOperationDao operationDao;

	@Autowired
	private IOperationDao operationdao;
	
	private List<Role> roles ;    //存储角色
	private List<Role> recRole;  //存储递归角色
	private List<Operation> operations; //存储用户操操作集合
	private List<Operation> roleOperation ; //存储角色操作集合
	
	//查询用户的所有角色及父级角色
	@Override
	public  List<Role> findRolesByUserId(int userId){
		recRole = new ArrayList<Role>() ;
		roles = new ArrayList<Role>();
		List<Role> list = userService.findRoleByUserId(userId);
			for(Role r:list){
				roles.add(r);
				int pid = r.getRoleExtendPId();
				if(pid>=0){
				 List<Role> r1=findRolesByRoleId(pid);
				}
			}
			//集合去重
			Set<Role> set = new  HashSet<Role>(); 
	        List<Role> newList = new ArrayList<Role>(); 
	         for (Role r:roles) {
	            if(set.add(r)){
	                newList.add(r);
	            }
	        }
		return newList;
	}
	// 递归查询角色
	private List<Role> findRolesByRoleId(int roleId){
		Role r = roleService.find(roleId);
		recRole.add(r);
		if(r.getRoleExtendPId()>=0){
			findRolesByRoleId(r.getRoleExtendPId());
		}
		return recRole;
	}
	// 查询用户对应操作
	@Override
	public List<Operation> findOperationsByUserId(int userId) {
		roleOperation = new ArrayList<Operation>();
		operations= new ArrayList<Operation>();
		List <Role> roleIds = findRolesByUserId(userId);
		List <Operation> opera = new ArrayList<Operation>();//临时存储操作集合（单一角色）
		for (Role role:roleIds){
			 opera = roleService.findOperationByRoleId(role.getId());
			 opera.removeAll(operations); // 移除所有和operations中一致的操作
			 operations.addAll(opera); //将剩余操作加入集合中
		}
		return operations;
	}
	// 查询角色对应操作
	@Override
	public List<Operation> findOperationsByRoleId(int roleId){
		recRole = new ArrayList<Role>() ;
		roleOperation = new ArrayList<Operation>();
		roleOperation = roleService.findOperationByRoleId(roleId);
		for(Operation o :roleOperation){
			o.setState(true);
		}
		Role r = roleService.find(roleId);
		if(r.getRoleExtendPId()>=0){
			findRolesByRoleId(r.getRoleExtendPId());
		}
		for(Role r1 :recRole){
			List<Operation> opera = roleService.findOperationByRoleId(r1.getId());
			opera.removeAll(roleOperation); // 移除所有和operations中一致的操作
			roleOperation.addAll(opera); //将剩余操作加入集合中
		}
		return roleOperation;
	}
	// 角色授权
	@Override
	public void authRole(int roleId, List <Integer> array){
		roleService.deleteRoleAndOperationsRelationByRoleId(roleId); //删除角色与操作关系集
		if(array.size() > 0){
			for(Integer i : array){
				roleService.saveRoleAndOperationRelation(roleId, i); //保存角色操作关系
			}
		}
	}
	// 用户授权
	@Override
	public void authUser(int userId, List <Integer> array){
		userService.deleteUserAndRolesByUserId(userId); //删除角色与用户关系集
		for(Integer i : array){
			userService.saveUserAndRoleRelation(userId, i);//保存用户角色关系
		}
	}
	
	//保存资源操作集
	@Override
	public void saveResourceAndOperation(Resource resource,String params){
		resourceService.save(resource);
		int resourceId = resource.getId();
		List<OperationDTO> operationList=conversionParams(params);
		for(OperationDTO s : operationList){
			saveOperation(resourceId,-1,s);		
		}
	}
	//更新资源操作集
	@Override
	public void updateResourceAndOperation(Resource resource,String params){
		resourceService.update(resource);
		int resourceId = resource.getId();
		//operationService.deleteAllByResourceId(resourceId);
		List<OperationDTO> operationList=conversionParams(params);
		for(OperationDTO s : operationList){
			saveOperation(resourceId,-1,s);		
		}
	}
	
	//整理前台params 参数
	private List<OperationDTO> conversionParams(String params){
		List<OperationDTO> odList = new ArrayList<OperationDTO>();
		List<OperationDTO> operationList=JsonUtil.jsonToList(params, OperationDTO.class);
		for (OperationDTO o:operationList){
			if("".equals(o.getOperation())||"".equals(o.getPermission())){
				continue;
			}else{
				if("".equals(o.getRelyName())||"无".equals(o.getRelyName())){
					odList.add(o);
					}
				for(OperationDTO od:operationList){
					if(o.getOperation().equals(od.getRelyName())){
						o.getList().add(od);
					}else{
						continue;
					}
				}
			}
		}
		return odList;
	}
	private void saveOperation(int resourceId,int rid,OperationDTO od){
		Operation operation =new Operation();
		if(od.getId() != 0){
			operation.setId(od.getId());
		}
		operation.setResourceId(resourceId);
		operation.setDisplayName(od.getOperation());
		operation.setPermission(od.getPermission());
		operation.setOperationName(PinyinUtil.converterToSpell(operation.getDisplayName()).split(",")[0]);
		operation.setOperationRId(rid);
		if(od.getId() != 0){
			operationService.update(operation);
		}else{
			operationService.save(operation);
		}
		int relyId = operation.getId();
		if(!CollectionUtils.isEmpty(od.getList())){
			for(OperationDTO o: od.getList()){
				saveOperation(resourceId,relyId,o);
			}
		}
	}
	//查询所有资源（包含操作信息）
	@Override
	public String findAllResourceInduleOperation(String params) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			// 当前只查询管理员
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Resource	> pg = resourceService.findPaginated(map);
			List<Resource> resourceList = pg.getData();
			for(Resource r: resourceList){
				Map<String,Object> ma = new HashMap<String,Object>();
				ma.put("resourceId", r.getId());
				if(r.getResourcePId() == -1){
					r.setResourcePName("无");
				}else{
					Resource resource = resourceService.find(r.getResourcePId());
					r.setResourcePName(resource.getResourceName());
				}
				List<Operation> oper = operationService.findAll(ma);
				String OperatingNum="";
				for (Operation operation : oper) {
					OperatingNum += operation.getDisplayName()+"、";
				}
				if(OperatingNum.length() == 0) {
					r.setOperatingNum(OperatingNum);
				} else {
					r.setOperatingNum(OperatingNum.substring(0,OperatingNum.length()-1));
				}
				r.setOperationList(oper);
			}
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", resourceList);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	//查询所有用户（包含角色信息）
	@Override
	public String findAllUserInduleRoles(String params) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<User> pg = userService.findPaginated(map);
			List<User> userList = pg.getData();
			for(User u: userList){
				String name = "";
				List<Role> roles = userService.findRoleByUserId(u.getId());
				if(roles.size() > 0){
					for (Role role : roles) {
						String displayName = role.getDisplayName();
						name +=displayName+",";
					}
					u.setRolesName(name.substring(0, name.length()-1));
				}
			}
			//序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", userList);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}

	@Override
	public void deleteResource(int id) {
		operationService.delete(id);
		operationDao.deleteRoleAndOperation(id);
	}
	
	
	@Override
	public List<TreeDTO> findRoleByUser(int userId) {
		List<TreeDTO> litsTree = new ArrayList<TreeDTO>();
		List<Role> list = roleService.findAll();
		List<Role> lsitRole= userService.findRoleByUserId(userId);//查询用户已经有的角色 修改状态
		for(Role r:list){
			if(r.getRoleExtendPId() == -1){
				TreeDTO tree = new TreeDTO();
				tree.setId(r.getId());
				tree.setName(r.getDisplayName());
				tree.setIndex(r.getGuid());
				if(r.getRoleRelyId() == -1){
					tree.setRelyId("-1");
				}else{
					Role re = roleDao.findRelyId(r.getRoleRelyId());
					tree.setRelyId(re.getGuid());
				}
				if(lsitRole.size() > 0){
					for (int i = 0; i < lsitRole.size(); i++) {
						if(lsitRole.get(i).getId() == r.getId()){
//							tree.setDisabled(true);
							tree.setChecked(true);
							break;
						}
					}
				}
				//litsTree.add(tree);
				List<Role> roleList = roleService.findRelyRole(r.getId());
				if(roleList.size() > 0){
					//litsTree = findByRoleId(roleList,tree,litsTree,userId);
					tree =findByRoleId(roleList,tree,userId);
					
				}
				litsTree.add(tree);
			}	
		}
		
		//集合去重
		/*Set<TreeDTO> set = new  HashSet<TreeDTO>(); 
		List<TreeDTO> lits = new ArrayList<TreeDTO>();
		for (TreeDTO treeDTO : litsTree) {
			if(set.add(treeDTO)){
				lits.add(treeDTO);
            }
		}
		return lits;*/
		return litsTree;
	}
	
/*	private List<TreeDTO> findByRoleId(List<Role> roleList,TreeDTO tree,List<TreeDTO> litsTree,int userId){
			List<TreeDTO> litsTree1 = new ArrayList<TreeDTO>();
			List<Role> lsitRole= userService.findRoleByUserId(userId);//查询用户已经有的角色 修改状态
			for (Role role : roleList) {
				TreeDTO tree1 = new TreeDTO();
				tree1.setId(role.getId());
				tree1.setName(role.getDisplayName());
				tree1.setIndex(role.getGuid());
				if(role.getRoleRelyId() == -1){
					tree1.setRelyId("-1");
				}else{
					Role re = roleDao.findRelyId(role.getRoleRelyId());
					tree1.setRelyId(re.getGuid());
				}
				if(lsitRole.size() > 0){
					for (int i = 0; i < lsitRole.size(); i++) {
						if(lsitRole.get(i).getId() == role.getId() && role.getId() != -1){
//							tree1.setDisabled(true);
							tree1.setChecked(true);
							break;
						}
					}
				}
				litsTree1.add(tree1);
				tree.setChildren(litsTree1);
//				litsTree.add(tree);
				List<Role> roleLists = roleService.findRelyRole(role.getId());
				if(roleLists.size() > 0){
					findByRoleId(roleLists,tree1,litsTree,userId);
				}
			}
		return litsTree;
	}*/ 
	
	
	private TreeDTO findByRoleId(List<Role> roleList,TreeDTO tree,int userId){
		List<TreeDTO> litsTree1 = new ArrayList<TreeDTO>();
		List<Role> lsitRole= userService.findRoleByUserId(userId);//查询用户已经有的角色 修改状态
		for (Role role : roleList) {
			TreeDTO tree1 = new TreeDTO();
			tree1.setId(role.getId());
			tree1.setName(role.getDisplayName());
			tree1.setIndex(role.getGuid());
			if(role.getRoleRelyId() == -1){
				tree1.setRelyId("-1");
			}else{
				Role re = roleDao.findRelyId(role.getRoleRelyId());
				tree1.setRelyId(re.getGuid());
			}
			if(lsitRole.size() > 0){
				for (int i = 0; i < lsitRole.size(); i++) {
					if(lsitRole.get(i).getId() == role.getId() && role.getId() != -1){
						tree1.setChecked(true);
						break;
					}
				}
			}
			litsTree1.add(tree1);
			tree.setChildren(litsTree1);
			List<Role> roleLists = roleService.findRelyRole(role.getId());
			if(roleLists.size() > 0){
				findByRoleId(roleLists,tree1,userId);
			}
		}
		return tree;
     } 
}


