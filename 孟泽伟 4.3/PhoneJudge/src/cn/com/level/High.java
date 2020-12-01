package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class High extends Huawei{
	public abstract void call();           //高端机至少需实现电话、短信、照相、蓝牙、gps功能
	public abstract void message();
	public abstract void photograph();
	public abstract void bluetooth();
	public abstract void gPS();
	public void introduce1() {
		super.introducehuawei();
		System.out.println("高端机型");
	}
}
