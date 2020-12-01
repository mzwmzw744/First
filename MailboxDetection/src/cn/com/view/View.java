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
		System.out.println("������E-mail");
		String Email = sc.next();
		boolean t ;
		t = (service.oneEmailRequirement(Email) && service.threeEmailRequirement(Email) && service.twoEmailRequirement(Email) && service.fourEmailRequirement(Email) && service.fiveEmailRequirement(Email));
		if(t) {
			System.out.println("����ɹ�");
		}else {
			System.out.println("����Emali���Ϸ���");
			return;
		}
	}
}
