package com.toughguy.alarmSystem.model.content;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Delayed {
	
	private int id;						//主键
	private String xzqh;				//行政区划
	private String dateStart;			//月开始时间
	private String dateStop;			//月结束时间
	private String delayedStart;		//延迟开始时间
	private String delayedDay;			//延迟天数
	private String delayedStop;			//延迟结束时间
	private String state;				//状态(状态为1可延迟     状态-1不可延迟)  
	
	public Delayed() {
		super();
	}

	public Delayed(int id, String xzqh, String dateStart, String dateStop, String delayedStart, String delayedDay,
			String delayedStop, String state) {
		super();
		this.id = id;
		this.xzqh = xzqh;
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.delayedStart = delayedStart;
		this.delayedDay = delayedDay;
		this.delayedStop = delayedStop;
		this.state = state;
	}

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
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateStop() {
		return dateStop;
	}
	public void setDateStop(String dateStop) {
		this.dateStop = dateStop;
	}
	public String getDelayedStart() {
		return delayedStart;
	}
	public void setDelayedStart(String delayedStart) {
		this.delayedStart = delayedStart;
	}
	public String getDelayedDay() {
		return delayedDay;
	}
	public void setDelayedDay(String delayedDay) {
		this.delayedDay = delayedDay;
	}
	public String getDelayedStop() {
		return delayedStop;
	}
	public void setDelayedStop(String delayedStop) {
		this.delayedStop = delayedStop;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Delayed [id=" + id + ", xzqh=" + xzqh + ", dateStart=" + dateStart + ", dateStop=" + dateStop
				+ ", delayedStart=" + delayedStart + ", delayedDay=" + delayedDay + ", delayedStop=" + delayedStop
				+ ", state=" + state + "]";
	}

	
}
