package com.toughguy.dataDisplay.model.content;

import java.util.Date;

public class FKDB {
	private String XZQHDM;		//行政区划代码
	private String FKDBH;		//反馈单编号
	private String JJDBH;		//接警单编号
	private String CJDBH;		//处警单编号
	private String FKDWDM;		//反馈单位代码
	private Date FKSJ;			//反馈时间
	private Date SJCJSJ;		//实际出警时间
	private Date DDXCSJ;		//到达现场时间
	private String JQLBDM;		//警情分类代码
	private String JQLXDM;		//警情类型代码
	private String JQXLDM;		//警情细类代码
	private Date JQFSSJ;		//警情发生时间
	private int FKDWXZB;		//反馈定位X坐标（处置现场位置）
	private int FKDWYZB;		//反馈定位Y坐标（处置现场位置）
	private String JQDJDM;		//警情等级代码
	private String CJQK;		//出警情况
	private String CDCLQK;		//出动车辆情况
	private String CDRYQK;		//出动人员情况
	private int JZRS;			//救助人数
	private String JZRSSM;		//救助人数说明
	private int JJRS;			//解救人数
	private String JJRSSM;		//解救人数说明
	private int SSRS;			//受伤人数
	private String SSRSSM;		//受伤人数说明
	private int SWRS;			//死亡人数
	private String SWRSSM;		//死亡人数说明
	private String CLJGDM;		//处理结果代码
	private String JQCLJG;		//警情处理结果
	private String SSQKMS;		//损失情况描述
	private String SFWHP;		//是否装载危险品
	private int SHJDCS;			//损坏机动车数
	private int SHFJDCS;		//损坏非机动车数
	private String JQZTDM;		//警情状态代码
	private int GXBS;			//更新标识
	private Date GXSJ;			//更新时间
	public String getXZQHDM() {
		return XZQHDM;
	}
	public void setXZQHDM(String xZQHDM) {
		XZQHDM = xZQHDM;
	}
	public String getFKDBH() {
		return FKDBH;
	}
	public void setFKDBH(String fKDBH) {
		FKDBH = fKDBH;
	}
	public String getJJDBH() {
		return JJDBH;
	}
	public void setJJDBH(String jJDBH) {
		JJDBH = jJDBH;
	}
	public String getCJDBH() {
		return CJDBH;
	}
	public void setCJDBH(String cJDBH) {
		CJDBH = cJDBH;
	}
	public String getFKDWDM() {
		return FKDWDM;
	}
	public void setFKDWDM(String fKDWDM) {
		FKDWDM = fKDWDM;
	}
	public Date getFKSJ() {
		return FKSJ;
	}
	public void setFKSJ(Date fKSJ) {
		FKSJ = fKSJ;
	}
	public Date getSJCJSJ() {
		return SJCJSJ;
	}
	public void setSJCJSJ(Date sJCJSJ) {
		SJCJSJ = sJCJSJ;
	}
	public Date getDDXCSJ() {
		return DDXCSJ;
	}
	public void setDDXCSJ(Date dDXCSJ) {
		DDXCSJ = dDXCSJ;
	}
	public String getJQLBDM() {
		return JQLBDM;
	}
	public void setJQLBDM(String jQLBDM) {
		JQLBDM = jQLBDM;
	}
	public String getJQLXDM() {
		return JQLXDM;
	}
	public void setJQLXDM(String jQLXDM) {
		JQLXDM = jQLXDM;
	}
	public String getJQXLDM() {
		return JQXLDM;
	}
	public void setJQXLDM(String jQXLDM) {
		JQXLDM = jQXLDM;
	}
	public Date getJQFSSJ() {
		return JQFSSJ;
	}
	public void setJQFSSJ(Date jQFSSJ) {
		JQFSSJ = jQFSSJ;
	}
	public int getFKDWXZB() {
		return FKDWXZB;
	}
	public void setFKDWXZB(int fKDWXZB) {
		FKDWXZB = fKDWXZB;
	}
	public int getFKDWYZB() {
		return FKDWYZB;
	}
	public void setFKDWYZB(int fKDWYZB) {
		FKDWYZB = fKDWYZB;
	}
	public String getJQDJDM() {
		return JQDJDM;
	}
	public void setJQDJDM(String jQDJDM) {
		JQDJDM = jQDJDM;
	}
	public String getCJQK() {
		return CJQK;
	}
	public void setCJQK(String cJQK) {
		CJQK = cJQK;
	}
	public String getCDCLQK() {
		return CDCLQK;
	}
	public void setCDCLQK(String cDCLQK) {
		CDCLQK = cDCLQK;
	}
	public String getCDRYQK() {
		return CDRYQK;
	}
	public void setCDRYQK(String cDRYQK) {
		CDRYQK = cDRYQK;
	}
	public int getJZRS() {
		return JZRS;
	}
	public void setJZRS(int jZRS) {
		JZRS = jZRS;
	}
	public String getJZRSSM() {
		return JZRSSM;
	}
	public void setJZRSSM(String jZRSSM) {
		JZRSSM = jZRSSM;
	}
	public int getJJRS() {
		return JJRS;
	}
	public void setJJRS(int jJRS) {
		JJRS = jJRS;
	}
	public String getJJRSSM() {
		return JJRSSM;
	}
	public void setJJRSSM(String jJRSSM) {
		JJRSSM = jJRSSM;
	}
	public int getSSRS() {
		return SSRS;
	}
	public void setSSRS(int sSRS) {
		SSRS = sSRS;
	}
	public String getSSRSSM() {
		return SSRSSM;
	}
	public void setSSRSSM(String sSRSSM) {
		SSRSSM = sSRSSM;
	}
	public int getSWRS() {
		return SWRS;
	}
	public void setSWRS(int sWRS) {
		SWRS = sWRS;
	}
	public String getSWRSSM() {
		return SWRSSM;
	}
	public void setSWRSSM(String sWRSSM) {
		SWRSSM = sWRSSM;
	}
	public String getCLJGDM() {
		return CLJGDM;
	}
	public void setCLJGDM(String cLJGDM) {
		CLJGDM = cLJGDM;
	}
	public String getJQCLJG() {
		return JQCLJG;
	}
	public void setJQCLJG(String jQCLJG) {
		JQCLJG = jQCLJG;
	}
	public String getSSQKMS() {
		return SSQKMS;
	}
	public void setSSQKMS(String sSQKMS) {
		SSQKMS = sSQKMS;
	}
	public String getSFWHP() {
		return SFWHP;
	}
	public void setSFWHP(String sFWHP) {
		SFWHP = sFWHP;
	}
	public int getSHJDCS() {
		return SHJDCS;
	}
	public void setSHJDCS(int sHJDCS) {
		SHJDCS = sHJDCS;
	}
	public int getSHFJDCS() {
		return SHFJDCS;
	}
	public void setSHFJDCS(int sHFJDCS) {
		SHFJDCS = sHFJDCS;
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
		return "FKDB [XZQHDM=" + XZQHDM + ", FKDBH=" + FKDBH + ", JJDBH=" + JJDBH + ", CJDBH=" + CJDBH + ", FKDWDM="
				+ FKDWDM + ", FKSJ=" + FKSJ + ", SJCJSJ=" + SJCJSJ + ", DDXCSJ=" + DDXCSJ + ", JQLBDM=" + JQLBDM
				+ ", JQLXDM=" + JQLXDM + ", JQXLDM=" + JQXLDM + ", JQFSSJ=" + JQFSSJ + ", FKDWXZB=" + FKDWXZB
				+ ", FKDWYZB=" + FKDWYZB + ", JQDJDM=" + JQDJDM + ", CJQK=" + CJQK + ", CDCLQK=" + CDCLQK + ", CDRYQK="
				+ CDRYQK + ", JZRS=" + JZRS + ", JZRSSM=" + JZRSSM + ", JJRS=" + JJRS + ", JJRSSM=" + JJRSSM + ", SSRS="
				+ SSRS + ", SSRSSM=" + SSRSSM + ", SWRS=" + SWRS + ", SWRSSM=" + SWRSSM + ", CLJGDM=" + CLJGDM
				+ ", JQCLJG=" + JQCLJG + ", SSQKMS=" + SSQKMS + ", SFWHP=" + SFWHP + ", SHJDCS=" + SHJDCS + ", SHFJDCS="
				+ SHFJDCS + ", JQZTDM=" + JQZTDM + ", GXBS=" + GXBS + ", GXSJ=" + GXSJ + "]";
	}

}
