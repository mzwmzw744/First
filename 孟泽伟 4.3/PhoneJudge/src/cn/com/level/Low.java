package cn.com.level;

import cn.com.huawei.Huawei;

public abstract class Low extends Huawei {
	public abstract void call();           //�Ͷ˻�������ʵ�ֵ绰�����Ź���
	public abstract void message();    
	public void introduce1() {
		super.introducehuawei();
		System.out.println("�Ͷ˻���");
	}
}
