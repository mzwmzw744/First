package com.hm.article.entity;

public class StoryType implements Comparable<StoryType> {

	private Integer storyTypeId;
	private String storytypeName;
	private Integer weight;

	public StoryType() {
		super();

	}

	public StoryType(Integer storyTypeId, String storytypeName, Integer weight) {
		super();
		this.storyTypeId = storyTypeId;
		this.storytypeName = storytypeName;
		this.weight = weight;
	}

	public Integer getStoryTypeId() {
		return storyTypeId;
	}

	public void setStoryTypeId(Integer storyTypeId) {
		this.storyTypeId = storyTypeId;
	}

	public String getStorytypeName() {
		return storytypeName;
	}

	public void setStorytypeName(String storytypeName) {
		this.storytypeName = storytypeName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "StoryType [storyTypeId=" + storyTypeId + ", storytypeName=" + storytypeName + ", weight=" + weight
				+ "]";
	}

	@Override
	public int compareTo(StoryType o) {
		
		return this.weight-o.weight;
	}

}
