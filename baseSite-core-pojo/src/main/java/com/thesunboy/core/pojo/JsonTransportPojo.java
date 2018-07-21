/**
 * 
 */
package com.thesunboy.core.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * <b>功能说明:</b>
 * <p>
 * 带有json传输功能的pojo
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
 * @CreateDate 2017-4-18 - 下午12:56:56
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
@JsonAutoDetect
public interface JsonTransportPojo extends TransportPojo
{
	public String toJsonString();
}
