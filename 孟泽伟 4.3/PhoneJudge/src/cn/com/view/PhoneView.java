package cn.com.view;

import java.util.Scanner;

import cn.com.model.P30;
import cn.com.model.P40;
import cn.com.model.V20;
import cn.com.model._7e;
import cn.com.model._8e;
import cn.com.phone.Phone;
import cn.com.show.Show;

public class PhoneView {
	public PhoneView(){
		Scanner sc = new Scanner(System.in);
		String phonedate[] = new String[]{"huawei P30","huawei P40","huawei V20","huawei V10","huawei 8e","huawei 7e"};
		System.out.println("已有型号");
		for(int i = 0;i < phonedate.length;i++) {
			System.out.print(phonedate[i]+"\t");
		}
		System.out.println("\n请输入手机型号");	
		String name = sc.nextLine();
		Show show = new Show();
		Phone phone = null;
		if(name.equals("huawei P30")){
			phone = new P30();
			show.showphone(phone);
		}
		else if(name.equals("huawei P40")){
			phone = new P40();
			show.showphone(phone);
		}
		else if(name.equals("huawei V20")){
			phone = new V20();
			show.showphone(phone);
		}
		else if(name.equals("huawei V10")){
			phone = new V20();
			show.showphone(phone);
		}
		else if(name.equals("huawei 8e")){
			phone = new _8e();
			show.showphone(phone);
		}
		else if(name.equals("huawei 7e")){
			phone = new _7e();
			show.showphone(phone);
		}else{
			System.out.println("输入型号不存在！");
		}
	}
}
