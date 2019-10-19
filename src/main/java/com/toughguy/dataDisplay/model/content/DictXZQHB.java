package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *字典表-行政区划表 实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class DictXZQHB  {
	private int jjslsx;    //接警数量
	private String xzqhdm;    //行政区划代码
	private String xzqhmc;   //行政区划名称
	public int getJjslsx() {
		return jjslsx;
	}
	public void setJjslsx(int jjslsx) {
		this.jjslsx = jjslsx;
	}
	public String getXzqhdm() {
		return xzqhdm;
	}
	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}
	public String getXzqhmc() {
		return xzqhmc;
	}
	public void setXzqhmc(String xzqhmc) {
		this.xzqhmc = xzqhmc;
	}
	@Override
	public String toString() {
		return "Baojingqingkuang [jjslsx=" + jjslsx + ", xzqhdm=" + xzqhdm + ", xzqhmc=" + xzqhmc + "]";
	}
	
	

	
	

	
}
