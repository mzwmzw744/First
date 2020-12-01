package cn.com.service;
/**
 * 邮箱的五大要求
 * 1、必须有@和.
 * 2、@和.不能出现在末尾和首位
 * 3、@有且只有一个
 * 4、@要在第一个.的前面
 * 5、@和.不能连在一起
 */
public interface Service {
	/**
	 * 1、必须有@和.  
	 * 
	 */
	boolean oneEmailRequirement(String str);
	
	/**
	 * 2、@和.不能出现在末尾和首位
	 */
	
	boolean twoEmailRequirement(String str);
	
	/**
	 * 3、@有且只有一个
	 * 
	 */
	
	
	boolean threeEmailRequirement(String str);
	
	
	/**
	 * 4、@要在第一个.的前面
	 */
	
	boolean fourEmailRequirement(String str);
	
	
	/**
	 * 5、@和.不能连在一起
	 */
	
	boolean fiveEmailRequirement(String str);
	
	
}
