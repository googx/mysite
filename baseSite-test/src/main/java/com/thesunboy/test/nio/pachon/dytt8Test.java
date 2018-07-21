package com.thesunboy.test.nio.pachon;

/**
 * <b>功能说明:</b><p>
 * 尝试使用nio来实现一个爬虫demo,主要目的是为了练习nio的使用 以及加强理解,以便以后进行封装
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-03-30 下午4:04
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */

public class dytt8Test
{


	public static void main(String[] args)
	{

		//1.创建相关线程.(nio用的select线程,和处理业务的线程)
		//2.创建各自需要用的队列
		//3.开启执行
		//3.1主业务的线程发起一个httprequest到nio队列里.
		//3.2nio线程从队列去取出需要发起请求的对象,使用相关的httpclietnApi请求,然后nio
	}




}
