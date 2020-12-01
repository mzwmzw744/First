package cn.com.servlet.http;

import java.io.IOException;

import javax.sql.rowset.serial.SerialException;

public abstract class HttpServlet {
	//在实例化后做初始化的方法
	protected void init() {
		
	}
	
	//和前端完成具体业务的方法
	protected void service(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
		if(request.getMethod().equalsIgnoreCase("get")){
			doGet(request, response);
		}else if(request.getMethod().equalsIgnoreCase("post")){
			doPost(request, response);
		}
	}
	
	//如果用户请求方式是Get方式，那么由service调用doGet方法
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
		
	}

	//如果用户请求方式是Post方式，那么由service调用doPost方法
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
	
	}
	//在对象销毁前最后一个执行的方法
	protected void destroy() {
		
	}
}
