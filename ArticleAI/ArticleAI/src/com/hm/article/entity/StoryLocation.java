package com.hm.article.entity;

public class StoryLocation {

	// 编号
	private Integer storyLocationId;
	// 地名
	private String storyLocationName;
	// 权重
	private Integer weight;
	private Integer storyTypeId;

	public Integer getStoryLocationId() {
		return storyLocationId;
	}

	public void setStoryLocationId(Integer storyLocationId) {
		this.storyLocationId = storyLocationId;
	}

	public String getStoryLocationName() {
		return storyLocationName;
	}

	public void setStoryLocationName(String storyLocationName) {
		this.storyLocationName = storyLocationName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getStoryTypeId() {
		return storyTypeId;
	}

	public void setStoryTypeId(Integer storyTypeId) {
		this.storyTypeId = storyTypeId;
	}

}
