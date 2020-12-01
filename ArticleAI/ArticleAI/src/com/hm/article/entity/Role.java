package com.hm.article.entity;

import java.util.List;

public class Role {

	private Integer roleId;
	private String roleName;
	private String roleSecondName;
	// 与此有关的关联关系
	private String roles;

	public Role() {
		super();
	}

	public Role(Integer roleId, String roleName, String roleSecondName, String roles) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleSecondName = roleSecondName;
		this.roles = roles;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleSecondName() {
		return roleSecondName;
	}

	public void setRoleSecondName(String roleSecondName) {
		this.roleSecondName = roleSecondName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleSecondName=" + roleSecondName + ", roles="
				+ roles + "]";
	}

}
