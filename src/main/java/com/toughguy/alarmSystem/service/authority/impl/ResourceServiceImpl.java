package com.toughguy.alarmSystem.service.authority.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.alarmSystem.dto.OperationDTO;
import com.toughguy.alarmSystem.dto.TreeDTO;
import com.toughguy.alarmSystem.model.authority.Operation;
import com.toughguy.alarmSystem.model.authority.Resource;
import com.toughguy.alarmSystem.model.authority.Role;
import com.toughguy.alarmSystem.persist.authority.prototype.IOperationDao;
import com.toughguy.alarmSystem.persist.authority.prototype.IResourceDao;
import com.toughguy.alarmSystem.persist.authority.prototype.IRoleDao;
import com.toughguy.alarmSystem.service.authority.prototype.IResourceService;
import com.toughguy.alarmSystem.service.impl.GenericServiceImpl;
;

@Service
public class ResourceServiceImpl extends GenericServiceImpl<Resource, Integer> implements IResourceService {
	
	@Autowired
	IOperationDao operationDao;
	@Autowired
	IRoleDao roleDao;

	@Override
	public List<Integer> findROsByResourceId(int resourceId) {

		return ((IResourceDao) dao).findROsByResourceId(resourceId);
	}

	@Override
	public List<TreeDTO> findResourceTree(int roleId) {
		List<Resource> resource = findAll(); // 查出所有资源
		for (Resource res : resource) {
			List<Operation> Operation = operationDao.findById(res.getId());
			for (Operation ope : Operation) {
				res.getOperationList().add(ope);
			}
		}
		return listToTree(resource, roleId); // 转化为树形结构
	}

	// 将集合转化为树形结构
	private List<TreeDTO> listToTree(List<Resource> roles, int roleId) {
		List<Resource> resourceTrees = new ArrayList<Resource>();
		for (Resource res : roles) {
			if (res.getResourcePId() == -1 || res.getResourcePId() == 0) {
				resourceTrees.add(res);
			}
			for (Resource r : roles) {
				if (r.getResourcePId() == res.getId()) {
					res.getList().add(r);
				}
			}
		}
		return tree(resourceTrees, roleId);
	}

	public List<TreeDTO> tree(List<Resource> roles, int roleId) {
		List<TreeDTO> treeList = new ArrayList<>();
		List<Operation> list = operationDao.findByRoleId(roleId); // 得到该角色所拥有的操作集合
		Role role = roleDao.find(roleId);
		List<Operation> operList = operationDao.findByRoleId(role.getRoleRelyId()); // 得到依赖角色的所有操作
		for (Resource r : roles) {
			 //判断本身是否有权限
			if (list.size() > 0) {     //本身拥有权限   
				if (operList.size() > 0) { // 有角色依赖 本身也有权限
					TreeDTO tree1 = new TreeDTO();
					tree1.setName(r.getResourceName());
					tree1.setId(r.getId());
					tree1.setType(-1);
					tree1.setIndex(r.getGuid());
					/* List<Resource> rList =((IResourceDao)
					 dao).findById(r.getId()); //查询出资源所拥有的下属资源
					 for (int j = 0; j < list.size(); j++) {
					 for (Resource resource : rList) {
					 if (resource.getId() == list.get(j).getResourceId()) {
					 tree1.setChecked("true");
					 break; //如果该角色有此操作的资源 跳出循环
					 } else {
					 tree1.setChecked("false");
					 }
					 }
					 }*/
					treeList.add(tree1);
					if (r.getList().size() != 0) {
						List<TreeDTO> treeList2 = new ArrayList<>();
						for (Resource r1 : r.getList()) {
							TreeDTO tree2 = new TreeDTO();
							tree2.setName(r1.getResourceName());
							tree2.setId(r1.getId());
							tree2.setType(-1);
							tree2.setIndex(r1.getGuid());
							/* for (int j = 0; j < list.size(); j++) {
							 if (r1.getId() == list.get(j).getResourceId()) {
							 tree2.setChecked("true");
							 break;
							 } else {
							 tree2.setChecked("false");
							 }
							 }*/
							treeList2.add(tree2);
							tree1.setChildren(treeList2);
							if (r1.getList().size() != 0) {
								tree(r1.getList(), roleId);
							} else if (r1.getOperationList().size() != 0) {
								List<TreeDTO> treeList3 = new ArrayList<>();
								for (Operation o : r1.getOperationList()) {
									if (r1.getId() == o.getResourceId()) {
										TreeDTO tree3 = new TreeDTO();
										tree3.setName(o.getDisplayName());
										tree3.setId(o.getId());
										tree3.setType(1);
										tree3.setIndex(o.getGuid());
										for (int i = 0; i < operList.size(); i++) {
											if (o.getId() == operList.get(i).getId()) {
												//判断角色依赖的操作与资源的操作是否相等  相等则将资源禁闭
//												if(operList.size() == r1.getOperationList().size()){
////													tree1.setDisabled(true);
////													treeList.add(tree1);
//													tree2.setDisabled(true);
//													tree2.setChecked(true);
//													treeList2.add(tree2);
//													tree1.setChildren(treeList2);
//													tree3.setDisabled(true);
//													tree3.setChecked(true);
//												}else{
													tree3.setDisabled(true);
													tree3.setChecked(true);
//												}
												break;
											} else {
												tree3.setDisabled(false);
												tree3.setChecked(false);
											}
										}
										for (int j = 0; j < list.size(); j++) {
											if (o.getId() == list.get(j).getId()) {
												tree3.setChecked(true);
												break;
											} else {
												tree3.setChecked(false);
											}
										}
										treeList3.add(tree3);
										tree2.setChildren(treeList3);
									}
								}
							}
						}
					} else if (r.getOperationList().size() != 0) {
						List<TreeDTO> treeList3 = new ArrayList<>();
						for (Operation o : r.getOperationList()) {
							if (r.getId() == o.getResourceId()) {
								TreeDTO tree3 = new TreeDTO();
								tree3.setName(o.getDisplayName());
								tree3.setId(o.getId());
								tree3.setType(1);
								tree3.setIndex(o.getGuid());
								for (int i = 0; i < operList.size(); i++) {
									if (o.getId() == operList.get(i).getId()) {
											tree3.setDisabled(true);
											tree3.setChecked(true);
										break;
									} else {
										tree3.setDisabled(false);
										tree3.setChecked(false);
									}
								}
								/*for (int j = 0; j < list.size(); j++) {
									if (o.getId() == list.get(j).getId()) {
										tree3.setChecked(true);
										break;
									} else {
										tree3.setChecked(false);
									}
								}*/
								treeList3.add(tree3);
								tree1.setChildren(treeList3);
							}
						}
					}
				} else {
					TreeDTO tree1 = new TreeDTO();
					tree1.setName(r.getResourceName());
					tree1.setId(r.getId());
					tree1.setType(-1);
					tree1.setIndex(r.getGuid());
					/* List<Resource> rList =((IResourceDao)
					 dao).findById(r.getId()); //查询出资源所拥有的下属资源
					 for (int j = 0; j < list.size(); j++) {
					 for (Resource resource : rList) {
					 if (resource.getId() == list.get(j).getResourceId()) {
					 tree1.setChecked("true");
					 break; //如果该角色有此操作的资源 跳出循环
					 } else {
					 tree1.setChecked("false");
					 }
					 }
					 }*/
					treeList.add(tree1);
					if (r.getList().size() != 0) {
						List<TreeDTO> treeList2 = new ArrayList<>();
						for (Resource r1 : r.getList()) {
							TreeDTO tree2 = new TreeDTO();
							tree2.setName(r1.getResourceName());
							tree2.setId(r1.getId());
							tree2.setType(-1);
							tree2.setIndex(r1.getGuid());
							/* for (int j = 0; j < list.size(); j++) {
							 if (r1.getId() == list.get(j).getResourceId()) {
							 tree2.setChecked("true");
							 break;
							 } else {
							 tree2.setChecked("false");
							 }
							 }*/
							treeList2.add(tree2);
							tree1.setChildren(treeList2);
							if (r1.getList().size() != 0) {
								tree(r1.getList(), roleId);
							} else if (r1.getOperationList().size() != 0) {
								List<TreeDTO> treeList3 = new ArrayList<>();
								for (Operation o : r1.getOperationList()) {
									if (r1.getId() == o.getResourceId()) {
										TreeDTO tree3 = new TreeDTO();
										tree3.setName(o.getDisplayName());
										tree3.setId(o.getId());
										tree3.setType(1);
										tree3.setIndex(o.getGuid());
										tree3.setDisabled(false);
										for (int j = 0; j < list.size(); j++) {
											if (o.getId() == list.get(j).getId()) {
												tree3.setChecked(true);
												break;
											} else {
												tree3.setChecked(false);
											}
										}
										treeList3.add(tree3);
										tree2.setChildren(treeList3);
									}
								}
							}
						}
					} else if (r.getOperationList().size() != 0) {
						List<TreeDTO> treeList3 = new ArrayList<>();
						for (Operation o : r.getOperationList()) {
							if (r.getId() == o.getResourceId()) {
								TreeDTO tree3 = new TreeDTO();
								tree3.setName(o.getDisplayName());
								tree3.setId(o.getId());
								tree3.setType(1);
								tree3.setIndex(o.getGuid());
								tree3.setDisabled(false);
								for (int j = 0; j < list.size(); j++) {
									if (o.getId() == list.get(j).getId()) {
										tree3.setChecked(true);
										break;
									} else {
										tree3.setChecked(false);
									}
								}
								treeList3.add(tree3);
								tree1.setChildren(treeList3);
							}
						}
					}
				}
			} else { // 该角色还未拥有权限
				if (operList.size() > 0) { // 有角色依赖 本身没有权限
					TreeDTO tree1 = new TreeDTO();
					tree1.setName(r.getResourceName());
					tree1.setId(r.getId());
					tree1.setType(-1);
					tree1.setIndex(r.getGuid());
					treeList.add(tree1);
					if (r.getList().size() != 0) {
						List<TreeDTO> treeList2 = new ArrayList<>();
						for (Resource r1 : r.getList()) {
							TreeDTO tree2 = new TreeDTO();
							tree2.setName(r1.getResourceName());
							tree2.setId(r1.getId());
							tree2.setType(-1);
							tree2.setIndex(r1.getGuid());
							treeList2.add(tree2);
							tree1.setChildren(treeList2);
							if (r1.getList().size() != 0) {
								tree(r1.getList(), roleId);
							} else if (r1.getOperationList().size() != 0) {
								List<TreeDTO> treeList3 = new ArrayList<>();
								for (Operation o : r1.getOperationList()) {
									if (r1.getId() == o.getResourceId()) {
										TreeDTO tree3 = new TreeDTO();
										tree3.setName(o.getDisplayName());
										tree3.setId(o.getId());
										tree3.setType(1);
										tree3.setIndex(o.getGuid());
										for (int i = 0; i < operList.size(); i++) {
											if (o.getId() == operList.get(i).getId()) {
												//判断资源的操作与角色依赖的操作个数是否相等
//												if(operList.size() == r.getOperationList().size()){
//													tree2.setDisabled(true);
//													tree2.setChecked(true);
//													treeList2.add(tree2);
//													tree1.setChildren(treeList2);
//													tree3.setDisabled(true);
//													tree3.setChecked(true);
//												}else{
													tree3.setDisabled(true);
													tree3.setChecked(true);
//												}
												break;
											} else {
												tree3.setDisabled(false);
											}
										}
										treeList3.add(tree3);
										tree2.setChildren(treeList3);
									}
								}
							}
						}
					} else if (r.getOperationList().size() != 0) {
						List<TreeDTO> treeList3 = new ArrayList<>();
						for (Operation o : r.getOperationList()) {
							if (r.getId() == o.getResourceId()) {
								TreeDTO tree3 = new TreeDTO();
								tree3.setName(o.getDisplayName());
								tree3.setId(o.getId());
								tree3.setType(1);
								tree3.setIndex(o.getGuid());
								for (int i = 0; i < operList.size(); i++) {
									if (o.getId() == operList.get(i).getId()) {
//										if(operList.size() == r.getOperationList().size()){
//											tree1.setDisabled(true);
//											treeList.add(tree1);
//											tree2.setDisabled(true);
//											treeList2.add(tree2);
//											tree1.setChildren(treeList2);
//									}else{
										tree3.setDisabled(true);
										tree3.setChecked(true);
//									}
										break;
									} else {
										tree3.setDisabled(false);
									}
								}
								treeList3.add(tree3);
								tree1.setChildren(treeList3);
							}
						}
					}
				} else {  // 没有角色依赖 本身无权限
					TreeDTO tree1 = new TreeDTO();
					tree1.setName(r.getResourceName());
					tree1.setId(r.getId());
					tree1.setType(-1);
					tree1.setIndex(r.getGuid());
					treeList.add(tree1);
					if (r.getList().size() != 0) {
						List<TreeDTO> treeList2 = new ArrayList<>();
						for (Resource r1 : r.getList()) {
							TreeDTO tree2 = new TreeDTO();
							tree2.setName(r1.getResourceName());
							tree2.setId(r1.getId());
							tree2.setType(-1);
							tree2.setIndex(r1.getGuid());
							treeList2.add(tree2);
							tree1.setChildren(treeList2);
							if (r1.getList().size() != 0) {
								tree(r1.getList(), roleId);
							} else if (r1.getOperationList().size() != 0) {
								List<TreeDTO> treeList3 = new ArrayList<>();
								for (Operation o : r1.getOperationList()) {
									if (r1.getId() == o.getResourceId()) {
										TreeDTO tree3 = new TreeDTO();
										tree3.setName(o.getDisplayName());
										tree3.setId(o.getId());
										tree3.setType(1);
										tree3.setIndex(o.getGuid());
										treeList3.add(tree3);
										tree2.setChildren(treeList3);
									}
								}
							}
						}
					} else if (r.getOperationList().size() != 0) {
						List<TreeDTO> treeList3 = new ArrayList<>();
						for (Operation o : r.getOperationList()) {
							if (r.getId() == o.getResourceId()) {
								TreeDTO tree3 = new TreeDTO();
								tree3.setName(o.getDisplayName());
								tree3.setId(o.getId());
								tree3.setType(1);
								tree3.setIndex(o.getGuid());
								treeList3.add(tree3);
								tree1.setChildren(treeList3);
							}
						}
					}
				}
			}
		}
		return treeList;
	}

	@Override
	public List<Resource> findByresourceName(String resourceName) {
		// TODO Auto-generated method stub
		return ((IResourceDao) dao).findByresourceName(resourceName);
	}

	
	 /**
	 * 查看资源
	 * @param resourceId
	 * @return Resource
	 */
	@Override
	public Resource checkResource(int resourceId) {
		Resource resource = ((IResourceDao) dao).find(resourceId);
		List<Operation> list = operationDao.findById(resourceId); //查出资源对应操作
		List<OperationDTO> listDTO = new ArrayList<>();
		if(list.size() > 0){
			for (Operation operation : list) {
				OperationDTO operationDTO = new OperationDTO();
				operationDTO.setId(operation.getId());
				operationDTO.setOperation(operation.getDisplayName());
				operationDTO.setPermission(operation.getPermission());
				if(operation.getOperationRId() == -1){
					operationDTO.setOperationRId(-1);
					operationDTO.setRelyName("无");
				}else{
					Operation operation2 = operationDao.findOperation(operation.getOperationRId());
					operationDTO.setRelyName(operation2.getDisplayName());
					operationDTO.setOperationRId(operation.getOperationRId());
				}
				listDTO.add(operationDTO);
			}
		}
		resource.setListDTO(listDTO);
		return resource;
	}

	

}
