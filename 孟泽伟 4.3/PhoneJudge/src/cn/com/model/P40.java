package cn.com.model;

import cn.com.level.High;

public class P40  extends High{
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

	@Override
	public void gPS() {
		// TODO Auto-generated method stub
		System.out.println("GPS");
	}

	@Override
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
