package com.hm.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hm.article.entity.Role;
import com.hm.article.entity.RoleDetail;
import com.hm.article.entity.StoryEvent;
import com.hm.article.entity.StoryLocation;
import com.hm.article.entity.StoryTimer;
import com.hm.article.entity.StoryType;

public class ArticleDao {

	// 加载驱动类
	static {
		try {
			// mysql jdbc 驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param articleKey
	 * @return
	 */
	public Role queryRole(String articleKey) {
		Connection connection = null;
		Role role = null;
		// JDBC模板化操作
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			//
			String sql = "SELECT role_id,role_name,role_second_name,to_role_id FROM role where role_name like ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + articleKey + "%");
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				// 封装数据Role
				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getNString("role_name"));
				role.setRoleSecondName(rs.getNString("role_second_name"));
				// 关联的人物ID，3，4，5
				role.setRoles(rs.getString("to_role_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	/*
	 * 8 得到角色的周边信息
	 */
	public List<RoleDetail> getRoleDetails(Role role) {

		return null;
	}

	/**
	 * 查询与此人物有关的其它人物信息
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> queryRoles(Role role) {
		Connection connection = null;
		List<Role> roles = new ArrayList<Role>();
		// JDBC模板化操作
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			//
			String sql = "SELECT role_id,role_name,role_second_name,to_role_id FROM role where role_id in ("
					+ role.getRoles() + ")";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				// 封装数据Role
				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getNString("role_name"));
				role.setRoleSecondName(rs.getNString("role_second_name"));
				// 关联的人物ID，3，4，5
				role.setRoles(rs.getString("to_role_id"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	
	public List<Role> queryAllRoles(Role role) {
		Connection connection = null;
		List<Role> roles = new ArrayList<Role>();
		// JDBC模板化操作
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			//
			String sql = "SELECT role_id,role_name,role_second_name,to_role_id FROM role ";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				// 封装数据Role
				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getNString("role_name"));
				role.setRoleSecondName(rs.getNString("role_second_name"));
				// 关联的人物ID，3，4，5
				role.setRoles(rs.getString("to_role_id"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	/**
	 * 查找角色的故事属性（标签）
	 * 
	 * @param role
	 * @return
	 */
	public List<StoryType> queryStoryType(Role role) {
		Connection connection = null;
		List<StoryType> storyTypes = new ArrayList<StoryType>();
		// JDBC模板化操作
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			//
			String sql = "SELECT rs.story_id storyId,rs.weight weight,\r\n"
					+ "st.story_name storyName FROM role r inner JOIN role_story rs \r\n"
					+ "on r.role_id=rs.role_id INNER JOIN story_type st on rs.story_id=st.story_id\r\n"
					+ "where r.role_id=" + role.getRoleId();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				// 封装数据Role
				StoryType storyType = new StoryType();
				storyType.setStoryTypeId(rs.getInt("storyId"));
				storyType.setStorytypeName(rs.getString("storyName"));
				storyType.setWeight(rs.getInt("weight"));
				storyTypes.add(storyType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storyTypes;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<StoryLocation> getAllLocation(List<StoryType> list) {

		Connection connection = null;
		List<StoryLocation> storyLocations = new ArrayList<StoryLocation>();
		// JDBC模板化操作
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			StringBuffer storyTypeIds = new StringBuffer();
			for (StoryType storyType : list) {
				storyTypeIds.append(storyType.getStoryTypeId()).append(",");
			}
			// 删除最后的一个逗号
			storyTypeIds.deleteCharAt(storyTypeIds.length() - 1);

			String sql = "SELECT l.location_id locationId,l.location_name locationName,\r\n"
					+ "l.location_desc,ls.story_id storyId,ls.weight weight,\r\n"
					+ "st.story_name FROM location l inner JOIN location_story ls\r\n"
					+ "on l.location_id=ls.location_id INNER JOIN story_type st\r\n"
					+ "on ls.story_id=st.story_id where ls.story_id in (" + storyTypeIds.toString()
					+ ") ORDER BY l.location_id";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				// 封装地点数据
				StoryLocation storyLocation = new StoryLocation();
				storyLocation.setStoryLocationId(rs.getInt("locationId"));
				storyLocation.setStoryLocationName(rs.getNString("locationName"));
				storyLocation.setStoryTypeId(rs.getInt("storyId"));
				storyLocation.setWeight(rs.getInt("weight"));
				storyLocations.add(storyLocation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storyLocations;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<StoryTimer> getAllTimer(List<StoryType> list) {
		//
		Connection connection = null;
		List<StoryTimer> storyTimers = new ArrayList<StoryTimer>();
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			StringBuffer storyTypeIds = new StringBuffer();
			for (StoryType storyType : list) {
				storyTypeIds.append(storyType.getStoryTypeId()).append(",");
			}
			// 删除最后的一个逗号
			storyTypeIds.deleteCharAt(storyTypeIds.length() - 1);

			String sql = "SELECT\r\n" + "t.timer_id timerId,\r\n" + "t.timer_name timerName,\r\n" + "t.timer_desc,\r\n"
					+ "ts.story_d storyId,\r\n" + "ts.weight weight,\r\n" + "st.story_name\r\n" + "FROM\r\n"
					+ "timer t\r\n" + "INNER JOIN timer_story ts\r\n" + "on t.timer_id=ts.timer_id\r\n"
					+ "INNER JOIN story_type st\r\n" + "on ts.story_d=st.story_id\r\n" + "where ts.story_d in  ("
					+ storyTypeIds.toString() + ")  ORDER BY t.timer_id,ts.weight desc";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				StoryTimer storyStoryTimer = new StoryTimer();
				storyStoryTimer.setStoryTimerId(rs.getInt("timerId"));
				storyStoryTimer.setStoryTimerName(rs.getString("timerName"));
				storyStoryTimer.setStroyTypeId(rs.getInt("storyId"));
				storyStoryTimer.setWeight(rs.getInt("weight"));
				storyTimers.add(storyStoryTimer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storyTimers;
	}

	public List<StoryEvent> getAllEvent(List<StoryType> list) {
		//
		Connection connection = null;
		List<StoryEvent> storyEvents = new ArrayList<StoryEvent>();
		String url = "jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, "root", "abc123");
			StringBuffer storyTypeIds = new StringBuffer();
			for (StoryType storyType : list) {
				storyTypeIds.append(storyType.getStoryTypeId()).append(",");
			}
			// 删除最后的一个逗号
			storyTypeIds.deleteCharAt(storyTypeIds.length() - 1);

			String sql = "SELECT\r\n" + "e.event_id eventId,\r\n" + "e.event_name evnetName,\r\n"
					+ "e.event_desc eventDesc,\r\n" + "es.story_id storyId,\r\n" + "es.weight weight,\r\n"
					+ "st.story_name\r\n" + "FROM\r\n" + "event e\r\n" + "INNER JOIN event_story es\r\n"
					+ "on e.event_id=es.evnet_id\r\n" + "INNER JOIN story_type st\r\n"
					+ "on es.story_id=st.story_id where es.story_id in (" + storyTypeIds.toString()
					+ ") ORDER BY e.event_id";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				StoryEvent storyStoryEvent = new StoryEvent();
				storyStoryEvent.setEventId(rs.getInt("eventId"));
				storyStoryEvent.setEventName(rs.getString("evnetName"));
				storyStoryEvent.setStroyTypeId(rs.getInt("storyId"));
				storyStoryEvent.setWeight(rs.getInt("weight"));
				storyStoryEvent.setEventDesc(rs.getString("eventDesc"));
				storyEvents.add(storyStoryEvent);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storyEvents;

	}
}
