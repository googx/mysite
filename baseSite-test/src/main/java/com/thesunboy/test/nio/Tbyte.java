/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.nio;

import java.util.Arrays;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-9-9 - 下午10:40:24 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Tbyte
{
	private final static MyLogger logger = MyLogFactory.getLogger(Tbyte.class, LogImplEnum.ConsoleLogImpl);

	/** 
	 *  简要功能说明:  
	 * <p>
	 *
	 * </p> 
	 * <b>注意事项: </b> 
	 *
	 * </br>
	 * @Author hx940929
	 * @Date 2017-9-9 - 下午10:40:24
	 * @param args
	 */
	public static void main(String[] args)
	{
		for (int i = 0; i < 10000; i++)
		{
			byte[] by = intToByteArray(i);
			// new Integer(i).byteValue();
			logger.info("main()==>数字字节值: " + Arrays.toString(by));
			int j = byteArrayToInt(by);
			logger.info("main()==>从字节转换回数字" + j);
		}
	}

	public static int byteArrayToInt(byte[] b)
	{
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int a)
	{
		return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF), (byte) (a & 0xFF) };
	}
}
