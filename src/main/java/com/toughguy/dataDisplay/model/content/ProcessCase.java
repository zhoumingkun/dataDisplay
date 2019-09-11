package com.toughguy.dataDisplay.model.content;

public class ProcessCase {
	
	private String lasj;		//立案时间
	private String ajlbmc;		//案件类别名称
	private String sljsdw;		//主办单位代码
	private String ajsl;		//案件数量
	
	public ProcessCase() {
		super();
	}
	public ProcessCase(String lasj, String ajlbmc, String sljsdw, String ajsl) {
		super();
		this.lasj = lasj;
		this.ajlbmc = ajlbmc;
		this.sljsdw = sljsdw;
		this.ajsl = ajsl;
	}
	public String getLasj() {
		return lasj;
	}
	public void setLasj(String lasj) {
		this.lasj = lasj;
	}
	public String getAjlbmc() {
		return ajlbmc;
	}
	public void setAjlbmc(String ajlbmc) {
		this.ajlbmc = ajlbmc;
	}
	public String getSljsdw() {
		return sljsdw;
	}
	public void setSljsdw(String sljsdw) {
		this.sljsdw = sljsdw;
	}
	public String getAjsl() {
		return ajsl;
	}
	public void setAjsl(String ajsl) {
		this.ajsl = ajsl;
	}
	@Override
	public String toString() {
		return "ProcessCase [lasj=" + lasj + ", ajlbmc=" + ajlbmc + ", sljsdw=" + sljsdw + ", ajsl=" + ajsl + "]";
	}

}
