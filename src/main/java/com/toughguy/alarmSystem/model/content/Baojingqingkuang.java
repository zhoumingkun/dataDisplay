package com.toughguy.alarmSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.alarmSystem.model.AbstractModel;
import com.toughguy.alarmSystem.util.JsonUtil;

/**
 *报警情况实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Baojingqingkuang extends AbstractModel {
	private int id;
	private String xzqh;    //行政区划
	private String tbdw;   //填报单位
	private String bjqk;  //报警情况
	private int wffzaj;       //违法犯罪案件
	private int zaaj;    //治安案件
	private int hzsg;       //火灾事故
	private int jtsg;        //交通事故
	private int zazhsg;   //治安灾害事故
	private int zhsg;   //灾害事故
	private int zs;   //来自杀源
	private int jf;   //纠纷
	private int jtbl;   //家庭暴力
	private int gmqz;   //公民求助
	private int zsxr;   //走失寻人
	private int jwjd;   //警务监督
	private int qt;   //其他
	private int hj;   //合计
	private String tbr;   //填报人
	private String tjyf;	//统计月份
	private String state;	//状态   正常1   非正常-1
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getBjqk() {
		return bjqk;
	}
	public void setBjqk(String bjqk) {
		this.bjqk = bjqk;
	}
	public String getTbdw() {
		return tbdw;
	}
	public void setTbdw(String tbdw) {
		this.tbdw = tbdw;
	}
	
	public int getWffzaj() {
		return wffzaj;
	}
	public void setWffzaj(int wffzaj) {
		this.wffzaj = wffzaj;
	}
	public int getZaaj() {
		return zaaj;
	}
	public void setZaaj(int zaaj) {
		this.zaaj = zaaj;
	}
	public int getHzsg() {
		return hzsg;
	}
	public void setHzsg(int hzsg) {
		this.hzsg = hzsg;
	}
	public int getJtsg() {
		return jtsg;
	}
	public void setJtsg(int jtsg) {
		this.jtsg = jtsg;
	}
	public int getZazhsg() {
		return zazhsg;
	}
	public void setZazhsg(int zazhsg) {
		this.zazhsg = zazhsg;
	}
	public int getZs() {
		return zs;
	}
	public void setZs(int zs) {
		this.zs = zs;
	}
	public int getJf() {
		return jf;
	}
	public void setJf(int jf) {
		this.jf = jf;
	}
	public int getJtbl() {
		return jtbl;
	}
	public void setJtbl(int jtbl) {
		this.jtbl = jtbl;
	}
	public int getGmqz() {
		return gmqz;
	}
	public void setGmqz(int gmqz) {
		this.gmqz = gmqz;
	}
	public int getZsxr() {
		return zsxr;
	}
	public void setZsxr(int zsxr) {
		this.zsxr = zsxr;
	}
	public int getJwjd() {
		return jwjd;
	}
	public void setJwjd(int jwjd) {
		this.jwjd = jwjd;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public int getHj() {
		return hj;
	}
	public void setHj(int hj) {
		this.hj = hj;
	}
	public String getTbr() {
		return tbr;
	}
	public void setTbr(String tbr) {
		this.tbr = tbr;
	}
	
	public int getZhsg() {
		return zhsg;
	}
	public void setZhsg(int zhsg) {
		this.zhsg = zhsg;
	}

	public String getTjyf() {
		return tjyf;
	}
	public void setTjyf(String tjyf) {
		this.tjyf = tjyf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Baojingqingkuang [id=" + id + ", xzqh=" + xzqh + ", tbdw=" + tbdw + ", bjqk=" + bjqk + ", wffzaj="
				+ wffzaj + ", zaaj=" + zaaj + ", hzsg=" + hzsg + ", jtsg=" + jtsg + ", zazhsg=" + zazhsg + ", zhsg="
				+ zhsg + ", zs=" + zs + ", jf=" + jf + ", jtbl=" + jtbl + ", gmqz=" + gmqz + ", zsxr=" + zsxr
				+ ", jwjd=" + jwjd + ", qt=" + qt + ", hj=" + hj + ", tbr=" + tbr + ", tjyf=" + tjyf + ", state="
				+ state + "]";
	}

	
	

	
}
