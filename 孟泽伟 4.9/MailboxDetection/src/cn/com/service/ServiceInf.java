package cn.com.service;

import java.lang.String;

public class ServiceInf implements Service{

	@Override
	public boolean oneEmailRequirement(String str) {
		// TODO Auto-generated method stub
		return str.contains("@") && str.contains(".");
		
	}

	@Override
	public boolean twoEmailRequirement(String str) {
		int t = str.indexOf("@")+1;
		int t1 =str.indexOf(".")+1;
		return (t != 1 && t != str.length() & t1 != 1 && t1 != str.length());
		// TODO Auto-generated method stub

	}

	@Override
	public boolean threeEmailRequirement(String str) {
		// TODO Auto-generated method stub
		int t = str.indexOf("@");
		int t1 = str.lastIndexOf("@");
		return t==t1;
	}

	@Override
	public boolean fourEmailRequirement(String str) {
		// TODO Auto-generated method stub
		int t = str.indexOf("@");
		int t1 =str.indexOf(".");
		return t < t1;
	}

	@Override
	public boolean fiveEmailRequirement(String str) {
		// TODO Auto-generated method stub
		int t = str.indexOf("@");
		int t1 =str.indexOf(".");
		return t != t1 - 1;
	}

	

}
