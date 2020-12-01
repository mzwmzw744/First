package cn.com.servlet.http;

import java.util.Map;

abstract class ServletFactory {
	private static Map<String, String> config;
	static {
		config = LoadConfig.getConfig();
	}
	public static HttpServlet createServlet(String uri) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		HttpServlet servlet = null;
		String classPath = config.get(uri);
		Class c = Class.forName(classPath);
		servlet = (HttpServlet)c.newInstance();
		return servlet;
	}
}
