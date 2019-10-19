package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *字典表-接警类型代码表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class DictJJLXDMB  {
	private String jjlxdm;    //接警类型代码
	private String jjlxmc;    //接警类型名称
	private String bz;   //备注
	public String getJjlxdm() {
		return jjlxdm;
		
	}
	public void setJjlxdm(String jjlxdm) {
		this.jjlxdm = jjlxdm;
	}
	public String getJjlxmc() {
		return jjlxmc;
	}
	public void setJjlxmc(String jjlxmc) {
		this.jjlxmc = jjlxmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "DictJJLXDMB [jjlxdm=" + jjlxdm + ", jjlxmc=" + jjlxmc + ", bz=" + bz + "]";
	}
	
	
	
	

	
	

	
}
