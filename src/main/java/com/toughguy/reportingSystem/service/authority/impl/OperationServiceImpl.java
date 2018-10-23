package com.toughguy.reportingSystem.service.authority.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.authority.Operation;
import com.toughguy.reportingSystem.model.authority.Resource;
import com.toughguy.reportingSystem.persist.authority.prototype.IOperationDao;
import com.toughguy.reportingSystem.service.authority.prototype.IOperationService;
import com.toughguy.reportingSystem.service.authority.prototype.IResourceService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

@Service
public class OperationServiceImpl extends GenericServiceImpl<Operation, Integer> implements IOperationService {
	
	@Autowired
	private IResourceService resourceService;
	
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
}
