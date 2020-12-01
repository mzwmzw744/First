package cn.com.servlet.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

/**
 * 会话线程
 * @author Lenovo
 *
 */
public class SessionThread extends Thread{

	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	private BufferedReader brFile;
	private static Map<String, String> config;
	private HttpServlet servlet;
	private static Map<String, HttpServlet> servlets;
	static{
		config = LoadConfig.getConfig();
		servlets = ServletDB.getServlets();
	}
	public SessionThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream(),true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = new HttpServletRequestImpl(br);
			if(!(request.getMethod().equalsIgnoreCase("get") || request.getMethod().equalsIgnoreCase("post"))){
				return;
			}
			if(!(request.getRequestURI().startsWith("/"))){
				return;
			}
			if(!(request.getProtocol().equalsIgnoreCase("http/1.0") || request.getProtocol().equalsIgnoreCase("http/1.1"))){
				return;
			}
			
			String filePath = request.getRequestURI();
			System.out.println(s.getInetAddress().getHostAddress()+"请求了："+filePath+"文件");
			if(filePath.toLowerCase().endsWith(".html") || filePath.toLowerCase().endsWith(".htm")){
				File f = new File(filePath.substring(1));
				if(f.exists()){
					if(f.isFile()){
						response = new HttpServletResponseImpl(pw, "200", request);
						brFile = new BufferedReader(new FileReader(f));
						String line = null;
						while((line = brFile.readLine()) != null){
							pw.println(line);
						}
					}else{
						response = new HttpServletResponseImpl(pw, "404", request);
					}
				}else{
					response = new HttpServletResponseImpl(pw, "404", request);
				}
			}else if(filePath.toLowerCase().endsWith(".jsp")){
				
			}else{
				String uri = request.getRequestURI();
				if(config.containsKey(uri)){
					if(servlets.containsKey(uri)){
						servlet = servlets.get(uri);
					}else{
						servlet = ServletFactory.createServlet(uri);
						servlets.put(uri, servlet);
						servlet.init();
					}
					response = new HttpServletResponseImpl(pw, "200", request);
					
					servlet.service(request, response);
				}else{
					response = new HttpServletResponseImpl(pw, "404", request);
				}
			}
			
			
		} catch(SocketException e){
			System.out.println("客户端退出");
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new HttpServletResponseImpl(pw, "500", request);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new HttpServletResponseImpl(pw, "500", request);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new HttpServletResponseImpl(pw, "500", request);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new HttpServletResponseImpl(pw, "500", request);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new HttpServletResponseImpl(pw, "500", request);
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(brFile != null){
				try {
					brFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pw != null){
				pw.close();
			}
			if(request.getProtocol().equalsIgnoreCase("http/1.0")){
				if(s != null){
					try {
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if(servlet != null){
			servlet.destroy();
		}
	}

}
