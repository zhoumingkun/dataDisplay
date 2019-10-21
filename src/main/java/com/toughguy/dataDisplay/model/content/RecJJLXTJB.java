package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *统计表表-报警类型统计表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class RecJJLXTJB  {
	private String xzqhdm;    //行政区划代码
	private String tjrq;      //统计日期
	private String jjlxdm;    //接警类型代码
	private int jjsl;         //接警数量
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
	public String getJjlxdm() {
		return jjlxdm;
	}
	public void setJjlxdm(String jjlxdm) {
		this.jjlxdm = jjlxdm;
	}
	public int getJjsl() {
		return jjsl;
	}
	public void setJjsl(int jjsl) {
		this.jjsl = jjsl;
	}
	@Override
	public String toString() {
		return "RecJJLXTJB [xzqhdm=" + xzqhdm + ", tjrq=" + tjrq + ", jjlxdm=" + jjlxdm + ", jjsl=" + jjsl + "]";
	}
	
}
