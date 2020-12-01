package cn.com.servlet.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebService {
	private ServerSocket ss;
	private Socket s;
	private boolean bool;
	
	public WebService(){
		try {
			ss = new ServerSocket(9001);
			System.out.println("服务器已经启动");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bool = true;
		init();
	}

	public void setBool(boolean bool){
		this.bool = bool;
	}
	private void init() {
		// TODO Auto-generated method stub
		while(bool){
			try {
				s = ss.accept();
				new SessionThread(s).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
