package cn.com.service;
/**
 * ��������Ҫ��
 * 1��������@��.
 * 2��@��.���ܳ�����ĩβ����λ
 * 3��@����ֻ��һ��
 * 4��@Ҫ�ڵ�һ��.��ǰ��
 * 5��@��.��������һ��
 */
public interface Service {
	/**
	 * 1��������@��.  
	 * 
	 */
	boolean oneEmailRequirement(String str);
	
	/**
	 * 2��@��.���ܳ�����ĩβ����λ
	 */
	
	boolean twoEmailRequirement(String str);
	
	/**
	 * 3��@����ֻ��һ��
	 * 
	 */
	
	
	boolean threeEmailRequirement(String str);
	
	
	/**
	 * 4��@Ҫ�ڵ�һ��.��ǰ��
	 */
	
	boolean fourEmailRequirement(String str);
	
	
	/**
	 * 5��@��.��������һ��
	 */
	
	boolean fiveEmailRequirement(String str);
	
	
}
