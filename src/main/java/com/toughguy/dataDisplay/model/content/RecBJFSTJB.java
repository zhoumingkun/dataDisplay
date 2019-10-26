package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *统计表表-报警方式统计表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class RecBJFSTJB  {
	private String tjrq;      //统计时间
	private String bjfsdm;    //报警方式代码
	private int jjsl;         //接警数量
	private String xzqhdm;    //行政区划代码
	public String getTjrq() {
		return tjrq;
	}
	public void setTjrq(String tjrq) {
		this.tjrq = tjrq;
	}
	public String getBjfsdm() {
		return bjfsdm;
	}
	public void setBjfsdm(String bjfsdm) {
		this.bjfsdm = bjfsdm;
	}
	public int getJjsl() {
		return jjsl;
	}
	public void setJjsl(int jjsl) {
		this.jjsl = jjsl;
	}
	public String getXzqhdm() {
		return xzqhdm;
	}
	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}
	@Override
	public String toString() {
		return "RecBJFSTJB [tjrq=" + tjrq + ", bjfsdm=" + bjfsdm + ", jjsl=" + jjsl + ", xzqhdm=" + xzqhdm + "]";
	}

}
