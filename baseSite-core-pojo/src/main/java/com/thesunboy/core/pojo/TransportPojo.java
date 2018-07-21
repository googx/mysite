/**
 * 
 */
package com.thesunboy.core.pojo;

import java.io.Serializable;

/**
 * <b>功能说明:</b>
 * <p>
 * 具有传输对象功能的pojo均应该实现该接口,用于不同应用之间的数据传输交换
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2017-4-18 - 下午12:54:01
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public interface TransportPojo extends Serializable
{
	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * s 用于异构系统,或者打印输出日志时,要求实现Pojo的必须实现Object的toString,方法, 可根据需要选择以json协议传输
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午12:55:09
	 */
	public String tostring();
}
