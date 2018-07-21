/**
 * 
 */
package com.thesunboy.test;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.utils.DateUtils;
import com.thesunboy.core.pojo.JsonTransportPojo;
import com.thesunboy.core.pojo.entity.SysAccountInfoTable;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b>
 * <p>
 * 
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
 * @CreateDate 2017-4-18 - 下午3:48:41
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TestPojo
{
	private final static MyLogger logger = MyLogFactory.getLogger(TestPojo.class, LogImplEnum.ConsoleLogImpl);

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param args
	 * @Author hx940929
	 * @Date 2017-4-18 - 下午3:48:41
	 */
	public static void main(String[] args)
	{
		SysAccountInfoTable pojo = new SysAccountInfoTable();
		pojo.setCreateTime(DateUtils.getCurrentDateTime());
		pojo.setCreateUser("createuser");
		pojo.setModifierUser("modiuser");
		pojo.setAgeInteger(1111);
		pojo.setUserName("asdf");
		pojo.setPassword("asdf");
		JsonTransportPojo pojo2 = pojo; 

		logger.info("main()==>" + pojo2.toString());
		logger.info("main()==>" + pojo2.toJsonString());
	}
}
