package com.toughguy.dataDisplay.model.content;

import java.util.Date;

public class CJDB {
	private String XZQHDM;				//行政区划代码
	private String CJDBH;				//处警单编号
	private String JJDBH;				//接警单编号
	private String CJDWDM;				//处警单位代码
	private String CJYJ;				//处警意见
	private String CDDWDM;				//出警单位代码
	private Date CJSJ;					//处警时间
	private Date PDJSSJ;				//派单接收时间
	private String CDRY;				//出动人员
	private String CDCL;				//出动车辆
	private String JQZTDM;				//警情状态代码
	private int GXBS;					//更新标识
	private Date GXSJ;					//更新时间
	public String getXZQHDM() {
		return XZQHDM;
	}
	public void setXZQHDM(String xZQHDM) {
		XZQHDM = xZQHDM;
	}
	public String getCJDBH() {
		return CJDBH;
	}
	public void setCJDBH(String cJDBH) {
		CJDBH = cJDBH;
	}
	public String getJJDBH() {
		return JJDBH;
	}
	public void setJJDBH(String jJDBH) {
		JJDBH = jJDBH;
	}
	public String getCJDWDM() {
		return CJDWDM;
	}
	public void setCJDWDM(String cJDWDM) {
		CJDWDM = cJDWDM;
	}
	public String getCJYJ() {
		return CJYJ;
	}
	public void setCJYJ(String cJYJ) {
		CJYJ = cJYJ;
	}
	public String getCDDWDM() {
		return CDDWDM;
	}
	public void setCDDWDM(String cDDWDM) {
		CDDWDM = cDDWDM;
	}
	public Date getCJSJ() {
		return CJSJ;
	}
	public void setCJSJ(Date cJSJ) {
		CJSJ = cJSJ;
	}
	public Date getPDJSSJ() {
		return PDJSSJ;
	}
	public void setPDJSSJ(Date pDJSSJ) {
		PDJSSJ = pDJSSJ;
	}
	public String getCDRY() {
		return CDRY;
	}
	public void setCDRY(String cDRY) {
		CDRY = cDRY;
	}
	public String getCDCL() {
		return CDCL;
	}
	public void setCDCL(String cDCL) {
		CDCL = cDCL;
	}
	public String getJQZTDM() {
		return JQZTDM;
	}
	public void setJQZTDM(String jQZTDM) {
		JQZTDM = jQZTDM;
	}
	public int getGXBS() {
		return GXBS;
	}
	public void setGXBS(int gXBS) {
		GXBS = gXBS;
	}
	public Date getGXSJ() {
		return GXSJ;
	}
	public void setGXSJ(Date gXSJ) {
		GXSJ = gXSJ;
	}
	@Override
	public String toString() {
		return "CJDB [XZQHDM=" + XZQHDM + ", CJDBH=" + CJDBH + ", JJDBH=" + JJDBH + ", CJDWDM=" + CJDWDM + ", CJYJ="
				+ CJYJ + ", CDDWDM=" + CDDWDM + ", CJSJ=" + CJSJ + ", PDJSSJ=" + PDJSSJ + ", CDRY=" + CDRY + ", CDCL="
				+ CDCL + ", JQZTDM=" + JQZTDM + ", GXBS=" + GXBS + ", GXSJ=" + GXSJ + "]";
	}

}
