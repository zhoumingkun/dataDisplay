package com.toughguy.educationSystem.dto;

import java.util.List;

import com.toughguy.educationSystem.util.JsonUtil;


/**
 * 转换成前端能使用的树结构
 * 
 * */
public class TreeDTO {
	
	private String name;
	private int id;
	private List<TreeDTO> children;
	private	int type;  // -1 资源 ；1操作
	private boolean disabled=false;  //是否禁用 ture/false
	private boolean checked=false;  //是否选中 ture/false
	private String index;  //唯一标识
	private String relyId;  //唯一标识
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public List<TreeDTO> getChildren() {
		return children;
	}


	public void setChildren(List<TreeDTO> children) {
		this.children = children;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public boolean getChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getRelyId() {
		return relyId;
	}


	public void setRelyId(String relyId) {
		this.relyId = relyId;
	}


	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
	
}
