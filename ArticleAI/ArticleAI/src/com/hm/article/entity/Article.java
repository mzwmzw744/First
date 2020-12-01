package com.hm.article.entity;

import java.text.MessageFormat;
import java.util.List;

/**
 * 1、关键字 2、显示模板 3、数据源（爬虫）
 * 
 * @author Administrator
 *
 */
public class Article {

	// 标题
	private String title;
	// 主人物
	private Role mainRole;
	// 配角人物
	private List<Role> roles;
	// 故事标签
	private List<StoryType> storyTypes;
	// 故事发生的地点
	private StoryLocation storyLocation;
	
	private StoryTimer storyTimer;
	
	private StoryEvent storyEvent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Role getMainRole() {
		return mainRole;
	}

	public void setMainRole(Role mainRole) {
		this.mainRole = mainRole;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<StoryType> getStoryTypes() {
		return storyTypes;
	}

	public void setStoryTypes(List<StoryType> storyTypes) {
		this.storyTypes = storyTypes;
	}

	// 时间，地点，事件

	// 文章显示模块
	public String toString() {
		String pattern = "{0} 和 {1} 在 {2},在 {3}, 发生了 {4} ";
		String infos = MessageFormat.format(pattern, new Object[] { this.mainRole.getRoleName(),
				this.roles.get(0).getRoleName(), "很久很久以前", "天上人间", "通过喜鹊搭桥进行约会" });
		return infos;
	}

	public StoryLocation getStoryLocation() {
		return storyLocation;
	}

	public void setStoryLocation(StoryLocation storyLocation) {
		this.storyLocation = storyLocation;
	}

	public StoryTimer getStoryTimer() {
		return storyTimer;
	}

	public void setStoryTimer(StoryTimer storyTimer) {
		this.storyTimer = storyTimer;
	}

	public StoryEvent getStoryEvent() {
		return storyEvent;
	}

	public void setStoryEvent(StoryEvent storyEvent) {
		this.storyEvent = storyEvent;
	}

}
