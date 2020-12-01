package cn.com.view;

import java.util.Scanner;

import cn.com.service.Service;
import cn.com.service.ServiceInf;

public class View {
	private Scanner sc;
	private Service service;
	
	
	public View(){
		sc = new Scanner(System.in);
		service = new ServiceInf();
		init();
	}
	
	public void init(){
		System.out.println("请输入E-mail");
		String Email = sc.next();
		boolean t ;
		t = (service.oneEmailRequirement(Email) && service.threeEmailRequirement(Email) && service.twoEmailRequirement(Email) && service.fourEmailRequirement(Email) && service.fiveEmailRequirement(Email));
		if(t) {
			System.out.println("输入成功");
		}else {
			System.out.println("输入Emali不合法！");
			return;
		}
	}
}
