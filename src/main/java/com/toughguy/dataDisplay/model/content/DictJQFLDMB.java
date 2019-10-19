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
public class DictJQFLDMB  {
	private String fldm;    //分类代码
	private String flmc;    //分类名称
	private String fllx;    //01分类 02类型 03细类
	private String sjdm;    //上级代码
	public String getFldm() {
		return fldm;
	}
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	public String getFlmc() {
		return flmc;
	}
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	public String getFllx() {
		return fllx;
	}
	public void setFllx(String fllx) {
		this.fllx = fllx;
	}
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	@Override
	public String toString() {
		return "DictJQFLDMB [fldm=" + fldm + ", flmc=" + flmc + ", fllx=" + fllx + ", sjdm=" + sjdm + "]";
	}
	
	

	
	

	
}
