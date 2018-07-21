/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.commons.utils;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-7-13 - 上午11:05:39 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Test03 {
	public static int k = 0;
	
	public static Test03 t1 = new Test03("t1");
	public static int i = print("i");
	public static int n = 99;
	
	public int j = print("j");
	
	static {
		print("静态块");
	}
	
	{
	    print("构造块");
	}
	  
	
	
	public  Test03(String str) {
		System.out.println("C2:"+(++k) + ":" + str + "    i=" + i + "  n=" + n);
	    ++n; 
	    ++ i;
	}

	private static int print(String str) {
		System.out.println("C1:"+(++k) +":" + str + "   i=" + i + "   n=" + n);
	    ++n;
	    return ++ i;
	}
	
	public static void main(String[] args) {
		Test03 t2 = new Test03("t2");
		t2.toString();
	}

}

