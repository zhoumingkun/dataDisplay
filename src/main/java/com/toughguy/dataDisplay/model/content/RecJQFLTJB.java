package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *统计表表-警情分类统计表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class RecJQFLTJB  {
	private String xzqhdm;    //行政区划代码
	private String tjrq;      //统计日期
	private String fldm;    //分类代码
	private int jjsl;         //接警数量
	private int fllx;         //分类类型
	private String fldmmc;    //分类代码名称    页面用
	private String sjdmmc;    //上级代码名称   页面用
	private float hb;         //环比
	public String getXzqhdm() {
		
		return xzqhdm;
	}
	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}
	public String getTjrq() {
		return tjrq;
	}
	public void setTjrq(String tjrq) {
		this.tjrq = tjrq;
	}
	public String getFldm() {
		return fldm;
	}
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	public int getJjsl() {
		return jjsl;
	}
	public void setJjsl(int jjsl) {
		this.jjsl = jjsl;
	}
	public int getFllx() {
		return fllx;
	}
	public void setFllx(int fllx) {
		this.fllx = fllx;
	}
	
	public String getFldmmc() {
		return fldmmc;
	}
	public void setFldmmc(String fldmmc) {
		this.fldmmc = fldmmc;
	}
	public String getSjdmmc() {
		return sjdmmc;
	}
	public void setSjdmmc(String sjdmmc) {
		this.sjdmmc = sjdmmc;
	}
	
	public float getHb() {
		return hb;
	}
	public void setHb(float hb) {
		this.hb = hb;
	}
	@Override
	public String toString() {
		return "RecJQFLTJB [xzqhdm=" + xzqhdm + ", tjrq=" + tjrq + ", fldm=" + fldm + ", jjsl=" + jjsl + ", fllx="
				+ fllx + ", fldmmc=" + fldmmc + ", sjdmmc=" + sjdmmc + ", hb=" + hb + "]";
	}
	
	
	
}
