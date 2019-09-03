package com.toughguy.dataDisplay.model.content;

public class RecGrade {
	
	private String gradedm;		//等级代码
	private String proportion;	//占比
	
	public String getGradedm() {
		return gradedm;
	}
	public void setGradedm(String gradedm) {
		this.gradedm = gradedm;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	@Override
	public String toString() {
		return "RecGrade [gradedm=" + gradedm + ", proportion=" + proportion + "]";
	}
	
	
}
