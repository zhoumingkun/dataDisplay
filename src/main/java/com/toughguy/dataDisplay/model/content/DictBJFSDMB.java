package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *字典表-报警方式代码表 实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class DictBJFSDMB  {
	private String bjfsdm;    //报警方式代码
	private String bjfsmc;    //报警方式名称
	private String bz;   //备注
	public String getBjfsdm() {
		return bjfsdm;
	}
	public void setBjfsdm(String bjfsdm) {
		this.bjfsdm = bjfsdm;
	}
	public String getBjfsmc() {
		return bjfsmc;
	}
	public void setBjfsmc(String bjfsmc) {
		this.bjfsmc = bjfsmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "DictBJFSDMB [bjfsdm=" + bjfsdm + ", bjfsmc=" + bjfsmc + ", bz=" + bz + "]";
	}
	
	
	

	
	

	
}
