package com.thesunboy.commons.config;

import com.thesunboy.commons.utils.DateUtils;

import com.thesunboy.utils.mylog.core.ELogLevel;
import com.thesunboy.utils.mylog.core.ILocalEnviConsole;

/**
 * 客户端环境实现该接口,可以在这里在接上其他的日志框架扩展类,该tag可能是传入这个消息的class名</br> 回头要改进下多实例的问题:
 * 如果一个项目存在多实例,比如像log4j那样传入class名,这种问题该如何解决,是同一个实例,还是不同实例,
 * 如果是多个实例,那应该保持配置对象的一致性,即使用同一个配置对象来构建.
 * 
 * 
 * @author hx940929
 * @CreateDate 2016-3-13 - 下午9:33:49
 * @ModifiedDate 2016-3-13 - 下午9:33:49
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public class MyLogConsoleHandle implements ILocalEnviConsole
{

	@Override
	// public void print(ELogLevel level, Class clazz, String tag, Object
	// content,
	// Object... msgDetail)
	// {
	public void print(ELogLevel level, String clazz, String tag, Object content, Object... msgDetail)
	{

		switch (level)
			{
				case info_1:
					System.out.println(log(clazz, tag, content));
					break;
				case error_2:// 对抛出的异常进行处理,发送,站内信,邮件等,针对同一异常发出的次数做出统计,超出一定额度(管理员设置),即做什么操作(管理员设置)
					System.err.println(log(clazz, tag, content));
					if(msgDetail != null)
					{
						handleError(tag, content, msgDetail);
					}
					break;
				case warn_3:
					System.err.println(log(clazz, tag, content));
					break;
				case debug_4:
					System.out.println(debugLog(clazz, tag, content));
					break;
				default:
					break;
			}
	}

	// private final StringBuffer builder = new StringBuffer();

	// @SuppressWarnings("unused")
	// private String log(Class clazz, String tag, Object content)
	// {
	// // 效果:[Tag]-[Time]::classname==> 内容
	// builder.setLength(0);
	// builder.append("[" + tag + "]-[");
	// builder.append(DateUtils.getCurrentDateStr());
	// builder.append("] :: [");
	// builder.append(clazz.getSimpleName() + "]==> \r\n");
	// builder.append(content);
	// String log = builder.toString();
	// // builder.delete(1, log.length());
	// return log;
	// }

	private final StringBuilder builder = new StringBuilder();

	/**
	 * 这里存在一个隐含的bug,当并发高的话,由于多线程的原因,会造成数据的不同步,陆续导致以下异常</br> 方法一:
	 * ,在结尾把对象置null导致空指针异常,</br> 方法二: 使用delete方法导致数组越界异常</br> 方法三:
	 * 使用builder.setLength
	 * (0);虽然解决了异常的问题.但是却还是因为多线程的原因造成数据不同步,是否考虑加锁呢,感觉又不是很必要,加锁势必会影响并发的性能
	 * ,看情况加吧,暂时不需要<b>报着试试的心态加了锁,虽然可以解决这个问题.但是可能会造成死锁的发生!!!!!!!!绝对不能加</b> </br>
	 * 结论: 由此可以引出一个问题
	 * 
	 * @param clazz
	 * @param tag
	 * @param content
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-18 - 下午3:49:14
	 */
	private String log(String className, String tag, Object content)
	{
		// 效果:[Tag]-[Time]::classname==> 内容
		synchronized (builder)
		{
			builder.setLength(0);
			builder.append("[" + tag + "]-[");
			builder.append(DateUtils.getCurrentDateTimeStr());
			builder.append("]::[");
			builder.append(className + "]==>");
			if(content != null)
			{
				builder.append(content);
			}

		}
		return builder.toString();
	}

	private final StringBuilder debugBuilder = new StringBuilder("[debug]-[");

	private String debugLog(String className, String tag, Object content)
	{
		// 效果:[Tag]-[Time]::classname==> 内容
		debugBuilder.setLength(9);
		debugBuilder.append(DateUtils.getCurrentMillTimeStr());
		debugBuilder.append(" ]>>[");
		debugBuilder.append(className + "]==>");
		debugBuilder.append(content);
		return debugBuilder.toString();
	}

	/**
	 * 处理异常信息
	 * 
	 * @param tag
	 *            日志来源标签
	 * @param content
	 *            日志主要输出内容
	 * @param ExceptionDetail
	 *            详细的异常信息,是Exception对象
	 * @Author hx940929
	 * @Date 2016-3-17 - 下午3:43:55
	 */
	private void handleError(String tag, Object content, Object... detail)
	{
		boolean flag_canDeal = false;
		Exception exception = null;
		Class<? extends Exception> targetClass = null;
		// 请注意,由于.此处的处理做完还要做其他事情,所以这里使用while循环的方式作为判断情况,
		// 注意要在每一个出口都要break;否则将造成死循环!!!!!!!
		while (detail != null)
		{ // 拿到Exception对象
			if(detail[0] != null && detail[0] instanceof Exception)
			{
				exception = (Exception) detail[0];
				flag_canDeal = true;
			}
			else
			{
				flag_canDeal = false;
				break;
			}
			// 拿到Class对象
			if(detail[1] != null /* && detail[1] instanceof */)
			{
				targetClass = (Class<? extends Exception>) detail[1];
				flag_canDeal = true;
			}
			else
			{
				flag_canDeal = false;
				break;
			}

			break;
		}
		// 得到目标对象之后,进行相关的处理,必须两个参数都存在才能进行处理
		if(flag_canDeal)
		{
			exception.printStackTrace();
			// try
			// {
			// throw new Exception(exception.getCause());
			// }
			// catch (Exception e)
			// {
			// // System.err.println("ConsoleHandler 处理异常信息失败!!!!");
			// exception.printStackTrace();
			// }
		}
		else if(exception != null)
		{
			// 如何exception不等于null则进行相关处理
		}
	}
}
