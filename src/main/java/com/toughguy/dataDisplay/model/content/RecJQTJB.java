package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *统计表表-警情统计表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class RecJQTJB  {
	private String xzqhdm;    //行政区划代码
	private String tjrq;      //统计日期
	private String dwmc;    //单位名称
	private int jjsl;         //接警数量
	private int yxjq;         //有效警情
	private int cjsl;         //处警数量
	private int fksl;         //反馈数量
	private float hb;         //环比
	private float yxhb;        //环比
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
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public int getJjsl() {
		return jjsl;
	}
	public void setJjsl(int jjsl) {
		this.jjsl = jjsl;
	}
	public int getYxjq() {
		return yxjq;
	}
	public void setYxjq(int yxjq) {
		this.yxjq = yxjq;
	}
	public int getCjsl() {
		return cjsl;
	}
	public void setCjsl(int cjsl) {
		this.cjsl = cjsl;
	}
	public int getFksl() {
		return fksl;
	}
	public void setFksl(int fksl) {
		this.fksl = fksl;
	}
	public float getHb() {
		return hb;
	}
	public void setHb(float hb) {
		this.hb = hb;
	}
	public float getYxhb() {
		return yxhb;
	}
	public void setYxhb(float yxhb) {
		this.yxhb = yxhb;
	}
	@Override
	public String toString() {
		return "RecJQTJB [xzqhdm=" + xzqhdm + ", tjrq=" + tjrq + ", dwmc=" + dwmc + ", jjsl=" + jjsl + ", yxjq=" + yxjq
				+ ", cjsl=" + cjsl + ", fksl=" + fksl + ", hb=" + hb + ", yxhb=" + yxhb + "]";
	}
}
