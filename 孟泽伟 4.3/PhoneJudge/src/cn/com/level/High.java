package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class High extends Huawei{
	public abstract void call();           //�߶˻�������ʵ�ֵ绰�����š����ࡢ������gps����
	public abstract void message();
	public abstract void photograph();
	public abstract void bluetooth();
	public abstract void gPS();
	public void introduce1() {
		super.introducehuawei();
		System.out.println("�߶˻���");
	}
}
