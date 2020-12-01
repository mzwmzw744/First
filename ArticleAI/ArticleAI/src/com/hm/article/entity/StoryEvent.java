package com.hm.article.entity;

public class StoryEvent {
	
	private Integer eventId;
	private String eventName;
	private Integer stroyTypeId;
	private Integer weight;
	private String eventDesc;
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Integer getStroyTypeId() {
		return stroyTypeId;
	}
	public void setStroyTypeId(Integer stroyTypeId) {
		this.stroyTypeId = stroyTypeId;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}


}
