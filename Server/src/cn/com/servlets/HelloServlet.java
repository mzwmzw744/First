package cn.com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.sql.rowset.serial.SerialException;

import cn.com.servlet.http.HttpServlet;
import cn.com.servlet.http.HttpServletRequest;
import cn.com.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws SerialException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>hello</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>hello world</h1>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
