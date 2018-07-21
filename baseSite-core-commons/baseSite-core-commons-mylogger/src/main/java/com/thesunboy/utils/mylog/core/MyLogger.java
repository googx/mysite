package com.thesunboy.utils.mylog.core;

/**
 * 该logger的实例可由ILogFactory的实现类获得
 * 
 * @author hx940929
 * @CreateDate 2016年1月31日 - 下午11:16:03
 * @ModifiedDate 2016年1月31日 - 下午11:16:03
 * @Encoding UTF-8
 * @Version 1.0
 * @Site www.thesunboy.com
 * @QQ 836845967
 */
public interface MyLogger
{
	public void debug(Object text);

	public void info(Object text);

	/**
	 * 异常错误信息
	 * 
	 * @param text
	 *            异常发生说明
	 * @param exception
	 *            异常相关exception对象
	 * @param classT
	 *            发生异常的Class
	 * @Author hx940929
	 * @Date 2016-3-17 - 下午3:29:17
	 */
	public void error(Object text, Exception e,
			Class<? extends Exception> classT);

	public void warn(Object text);

	public boolean isDebug();

	// abstract String BasePrint(String text);

	public enum LogImplEnum
	{
		ConsoleLogImpl
		{

			@Override
			public String getTypeCode()
			{
				// TODO Auto-generated method stub
				return "=console";
			}

		},
		LocalDBLogImpl
		{

			@Override
			public String getTypeCode()
			{
				// TODO Auto-generated method stub
				return "=localDB";
			}
		},
		RemoteHttpLogImpl
		{

			@Override
			public String getTypeCode()
			{
				// TODO Auto-generated method stub
				return "=remoteHttp";
			}
		},
		LocalFileLogImpl
		{

			@Override
			public String getTypeCode()
			{
				// TODO Auto-generated method stub
				return "=localFile";
			}
		};

		public abstract String getTypeCode();
	}

}
