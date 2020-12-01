package cn.com.servlet.http;

import java.io.IOException;

import javax.sql.rowset.serial.SerialException;

public abstract class HttpServlet {
	//��ʵ����������ʼ���ķ���
	protected void init() {
		
	}
	
	//��ǰ����ɾ���ҵ��ķ���
	protected void service(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
		if(request.getMethod().equalsIgnoreCase("get")){
			doGet(request, response);
		}else if(request.getMethod().equalsIgnoreCase("post")){
			doPost(request, response);
		}
	}
	
	//����û�����ʽ��Get��ʽ����ô��service����doGet����
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
		
	}

	//����û�����ʽ��Post��ʽ����ô��service����doPost����
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws SerialException,IOException{
	
	}
	//�ڶ�������ǰ���һ��ִ�еķ���
	protected void destroy() {
		
	}
}
