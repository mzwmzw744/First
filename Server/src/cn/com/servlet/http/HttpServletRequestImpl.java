package cn.com.servlet.http;

import java.io.BufferedReader;
import java.io.IOException;

class HttpServletRequestImpl implements HttpServletRequest{

	private String head;
	private BufferedReader br;
	public HttpServletRequestImpl(BufferedReader br) throws IOException{
		this.br = br;
		this.head = br.readLine();
	}
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return head.split(" ")[0];
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return head.split(" ")[2];
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return head.split(" ")[1];
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return head;
	}

}
