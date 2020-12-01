package com.hm.article.entity;

import java.text.MessageFormat;
import java.util.List;

/**
 * 1���ؼ��� 2����ʾģ�� 3������Դ�����棩
 * 
 * @author Administrator
 *
 */
public class Article {

	// ����
	private String title;
	// ������
	private Role mainRole;
	// �������
	private List<Role> roles;
	// ���±�ǩ
	private List<StoryType> storyTypes;
	// ���·����ĵص�
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

	// ʱ�䣬�ص㣬�¼�

	// ������ʾģ��
	public String toString() {
		String pattern = "{0} �� {1} �� {2},�� {3}, ������ {4} ";
		String infos = MessageFormat.format(pattern, new Object[] { this.mainRole.getRoleName(),
				this.roles.get(0).getRoleName(), "�ܾúܾ���ǰ", "�����˼�", "ͨ��ϲȵ���Ž���Լ��" });
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
