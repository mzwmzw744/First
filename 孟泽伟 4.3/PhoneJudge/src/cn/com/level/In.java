package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class In extends Huawei {                                                     
	public abstract void call();           //中端机至少需实现电话、短信、照相、蓝牙功能
	public abstract void message();
	public abstract void photograph();
	public abstract void bluetooth();
	public void introduce1() {
		super.introducehuawei();
		System.out.println("中端机型");
	};
}
