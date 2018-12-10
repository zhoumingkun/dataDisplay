package com.toughguy.educationSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.educationSystem.model.AbstractModel;
import com.toughguy.educationSystem.util.JsonUtil;

/**
 * 心理测试题实体类
 * @author ZMK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Test extends AbstractModel {
	private int id;
	private int type;     //类型（1性格，2情感，3健康，4人际）
	private String title;    //标题
	private String author;   //作者
	private String summarize;//概述
	private String image;    //图片
	private int state;       //状态
	private int hits;        //浏览量
	private String form;     //试题形式
	private String source;   //来源
	
	private int testerPassSum;  //测试合格人次(页面使用)
	private int testerFailureSum;   //测试不合格人次(页面使用)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSummarize() {
		return summarize;
	}
	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getTesterPassSum() {
		return testerPassSum;
	}
	public void setTesterPassSum(int testerPassSum) {
		this.testerPassSum = testerPassSum;
	}
	public int getTesterFailureSum() {
		return testerFailureSum;
	}
	public void setTesterFailureSum(int testerFailureSum) {
		this.testerFailureSum = testerFailureSum;
	}
	 

	
}
