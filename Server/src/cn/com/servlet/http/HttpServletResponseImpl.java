package cn.com.servlet.http;

import java.io.PrintWriter;

class HttpServletResponseImpl implements HttpServletResponse{

	private PrintWriter pw;
	public HttpServletResponseImpl(PrintWriter pw,String stat,HttpServletRequest request){
		this.pw = pw;
		pw.println(request.getProtocol()+" "+stat+" OK\r\nContent-type: text/html;\r\n\r\n");
	}
	

	@Override
	public PrintWriter getWriter() {
		// TODO Auto-generated method stub
		return pw;
	}

	

}
