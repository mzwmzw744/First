package com.hm.article.entity;

public class StoryTimer {

	private Integer storyTimerId;
	private String storyTimerName;
	private Integer stroyTypeId;
	private Integer weight;

	public Integer getStoryTimerId() {
		return storyTimerId;
	}

	public void setStoryTimerId(Integer storyTimerId) {
		this.storyTimerId = storyTimerId;
	}

	public String getStoryTimerName() {
		return storyTimerName;
	}

	public void setStoryTimerName(String storyTimerName) {
		this.storyTimerName = storyTimerName;
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

	@Override
	public String toString() {
		return "StoryTimer [storyTimerId=" + storyTimerId + ", storyTimerName=" + storyTimerName + ", stroyTypeId="
				+ stroyTypeId + ", weight=" + weight + "]";
	}

}
