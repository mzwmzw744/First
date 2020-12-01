package cn.com.servlet.http;

import java.util.HashMap;
import java.util.Map;

abstract class ServletDB {
	private ServletDB(){
		
	}
	
	private static Map<String, HttpServlet> servlets;
	
	static{
		servlets = new HashMap<String, HttpServlet>();
	}
	
	public static Map<String, HttpServlet> getServlets(){
		return servlets;
	}
}
