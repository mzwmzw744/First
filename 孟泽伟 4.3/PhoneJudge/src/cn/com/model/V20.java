package cn.com.model;

import cn.com.level.High;
import cn.com.level.In;

public class V20  extends In{
	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println("电话");
	}

	@Override
	public void message() {
		// TODO Auto-generated method stub
		System.out.println("短信");
	}

	@Override
	public void photograph() {
		// TODO Auto-generated method stub
		System.out.println("照相");
	}

	@Override
	public void bluetooth() {
		// TODO Auto-generated method stub
		System.out.println("蓝牙");
	}
	
	public void introduce() {
		// TODO Auto-generated method stub
		super.introduce1();
		System.out.println("功能有");
		this.call();
		this.message();
		this.photograph();
		this.bluetooth();
		this.gPS();
	}
	
}
