package com.toughguy.alarmSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.alarmSystem.model.AbstractModel;
import com.toughguy.alarmSystem.util.JsonUtil;

/**
 * 微信小程序元素获取传参实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class MaterialParam  {
	private String type;//素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	private String offset;//从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	private String count;//返回素材的数量，取值在1到20之间
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
	
	 

}
