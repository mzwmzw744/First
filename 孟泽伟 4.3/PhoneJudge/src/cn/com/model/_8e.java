package cn.com.model;

import cn.com.level.High;
import cn.com.level.Low;

public class _8e  extends Low{
	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println("�绰");
	}
	
	@Override
	public void message() {
		// TODO Auto-generated method stub
		System.out.println("����");
	}

	@Override
	public void photograph() {
		// TODO Auto-generated method stub
		System.out.println("����");
	}

	@Override
	public void introduce() {
		// TODO Auto-generated method stub
		super.introduce1();
		System.out.println("������");
		this.call();
		this.message();
		this.photograph();
	}
	
}
