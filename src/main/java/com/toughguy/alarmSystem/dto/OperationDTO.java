package com.toughguy.alarmSystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.toughguy.alarmSystem.util.JsonUtil;

public class OperationDTO {
	
private int id;
	
	private String operation;
	
	private String permission;
	
	private String relyName ="";
	
	private int operationRId;
	
	private List<OperationDTO> list = new ArrayList<OperationDTO>();
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<OperationDTO> getList() {
		return list;
	}
	public void setList(List<OperationDTO> list) {
		this.list = list;
	}
	public String getRelyName() {
		return relyName;
	}
	public void setRelyName(String rName) {
		this.relyName = rName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public int getOperationRId() {
		return operationRId;
	}
	public void setOperationRId(int operationRId) {
		this.operationRId = operationRId;
	}
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}
