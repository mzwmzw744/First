package cn.com.servlet.http;

public interface HttpServletRequest {
	String getMethod();
	String getProtocol();
	String getRequestURI();
	String getHeader();
}
