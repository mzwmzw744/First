package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class In extends Huawei {                                                     
	public abstract void call();           //�ж˻�������ʵ�ֵ绰�����š����ࡢ��������
	public abstract void message();
	public abstract void photograph();
	public abstract void bluetooth();
	public void introduce1() {
		super.introducehuawei();
		System.out.println("�ж˻���");
	};
}
