/**
 * 
 */
package com.thesunboy.core.pojo.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesunboy.core.pojo.JsonTransportPojo;
import com.thesunboy.core.pojo.TransportPojo;

/**
 * <b>功能说明:</b>
 * <p>
 * basesite系统中,System表的一些基本属性,所有的System表应该基于此类型进行相应的扩展
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
 * @CreateDate 2017-4-18 - 下午3:04:13
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public abstract class AbsPojo implements JsonTransportPojo, TransportPojo
{
	protected static final ObjectMapper mapper = new ObjectMapper();
	// can use static singleton, inject: just make sure to reuse!
	private static final long serialVersionUID = 4070523519428871499L;

	/**
	 * 简要功能说明:
	 * <p>
	 * 返回实体类的json字符串
	 * </p>
	 * <b> 注意事项: </b> 如果某些变量不需要加入json中,或者有非空,非Null,等等特殊需求,需要使用com.fasterxml.
	 * jackson相关api自行实现 </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午3:04:39
	 */
	@Override
	public String toJsonString()
	{
		try
		{
			return mapper.writeValueAsString(this);
		}
		catch (JsonProcessingException e)
		{
			// TODO hx940929 by 上午10:18:13
			// TODO 暂时不使用自定义异常
			throw new RuntimeException(e);
		}

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午3:06:06
	 */
	@Override
	public String tostring()
	{
		String tostring = this.toString();
		if(tostring == null) { return super.toString(); }
		return tostring;
	}

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 要求表的实体类返回非json字符串的常规toString,可以使用IDE自动生成
	 * </p>
	 * <b> 注意事项: </b> 如果该方法返回了一个null, 抽象将会自动使用Object.toString(),方法 </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午3:07:45
	 */
	public abstract String toString();
}
