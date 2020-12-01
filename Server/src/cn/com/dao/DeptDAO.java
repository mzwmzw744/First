package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.DeptBean;
import cn.com.db.DBUtil;

public class DeptDAO {
	public List<DeptBean> getDeptInfo(){
		List<DeptBean> list = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from dept";
		conn = DBUtil.getConn();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			DeptBean db = null;
			list = new ArrayList<DeptBean>();
			while(rs.next()){
				db = new DeptBean();
				db.setDeName(rs.getString("dname"));
				db.setDeptNo(rs.getInt("deptno"));
				db.setLoc(rs.getString("loc"));
				list.add(db);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.free(conn, pstm, rs);
		}
		return list;
	}
}
