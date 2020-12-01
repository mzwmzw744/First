package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class Low extends Huawei {
	public abstract void call();           //低端机至少需实现电话、短信功能
	public abstract void message();    
	public void introduce1() {
		super.introducehuawei();
		System.out.println("低端机型");
	}
}
