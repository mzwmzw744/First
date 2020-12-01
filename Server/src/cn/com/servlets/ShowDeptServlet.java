package cn.com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import cn.com.beans.DeptBean;
import cn.com.dao.DeptDAO;
import cn.com.servlet.http.HttpServlet;
import cn.com.servlet.http.HttpServletRequest;
import cn.com.servlet.http.HttpServletResponse;

public class ShowDeptServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SerialException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		DeptDAO dao = new DeptDAO();
		List<DeptBean> list = dao.getDeptInfo();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>show Dept</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<table border = '1'");
		pw.println("<tr>");
		pw.println("<th>deptNo</th>");
		pw.println("<th>dname</th>");
		pw.println("<th>loc</th>");
		pw.println("</tr>");
		for(DeptBean db : list){
			pw.println("<tr>");
			pw.println("<td>"+db.getDeptNo()+"</td>");
			pw.println("<th>"+db.getDeName()+"</th>");
			pw.println("<th>"+db.getLoc()+"</th>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
