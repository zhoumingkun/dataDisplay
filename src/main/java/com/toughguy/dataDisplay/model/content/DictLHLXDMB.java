package com.toughguy.dataDisplay.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.dataDisplay.model.AbstractModel;
import com.toughguy.dataDisplay.util.JsonUtil;

/**
 *字典表-来话类型代码表  实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class DictLHLXDMB  {
	private String lhlxdm;    //来话类型代码
	private String lhlxmc;    //来话类型名称
	private String bz;   //备注
	public String getLhlxdm() {
		return lhlxdm;
	}
	public void setLhlxdm(String lhlxdm) {
		this.lhlxdm = lhlxdm;
	}
	public String getLhlxmc() {
		return lhlxmc;
	}
	public void setLhlxmc(String lhlxmc) {
		this.lhlxmc = lhlxmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "DictLHLXDMB [lhlxdm=" + lhlxdm + ", lhlxmc=" + lhlxmc + ", bz=" + bz + "]";
	}
	
	

	
	

	
}
