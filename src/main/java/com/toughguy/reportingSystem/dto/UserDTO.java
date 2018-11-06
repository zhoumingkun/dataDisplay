package com.toughguy.reportingSystem.dto;

import com.toughguy.reportingSystem.util.JsonUtil;

/**
 * 用户基本信息（前台）
 * 
 * */
public class UserDTO {
	
	private int id; //用户id
	private String userName;  //用户名
	private String phone;  //电话
	private String email;  //邮箱
	private int libraryId; //库id
	
	private String libraryName; //库名

	private String permissions; //操作名 拼接字符串
	private String resourceName;//资源名称 拼接字符串
	private String roleName;//角色名称
	
	private String token;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}	
	
	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}	

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
	
}
