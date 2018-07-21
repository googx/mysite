package com.thesunboy.utils.mylog.core;

/**
 * 子类继承controlerLog抽象类，作具体的日志实现，底层的接口和抽象类做日志的配置和线程的管理
 * 这样就可以将此日志模块从系统中分离出去。其他不同项目中都可以原封不动的使用，不管是se还是ee抑或者android
 * 2月20继续更新,由于很久没继续这个项目了
 * ,今天来继续完成,心得:一开始是想以静态实例做单例的模式,但是又想要做多个不同的实现类,这有违了一开始的设计思想,
 * 原先的设计太过注重静态单例了,现在认为应该全部重新构思
 * ,比如静态单例对象不应该交由日志的实现和接口模块进行管理,应该先基于接口给出日志的实现,暂且不管单例,
 * 然后在专门设计一个管理器,用来维护线程之间的单例对象, 所以在日志的模块上不需要有任何的静态对象的声明,但是只有同一个包中才可以对对象的实例
 * 要不要以静态单例创建对象,由客户端决定,当然这里也会写一个工厂的实现类用于返回各种不同持久化日志的实现类
 * 
 * @author hx940929
 * @CreateDate 2016年1月23日 - 下午11:54:54
 * @ModifiedDate 2016年1月23日 - 下午11:54:54
 * @Encoding UTF-8
 * @Version 1.0
 * @QQ 836845967
 */
public class ConsoleLogImpl extends AControlerLog
{

	private boolean consoleIsAvailable = false;
	private ILocalEnviConsole console = null;
	@SuppressWarnings("rawtypes")
	private final Class mClass;
	private final String mClassName;

	@Override
	public void lazyInit()
	{

	}

	@SuppressWarnings("rawtypes")
	public ConsoleLogImpl(Class clazz, LogConfiger configer)
	{
		super(configer);
		setConsole(configer.getLocalEnviConsole());
		this.mClass = clazz;
		this.mClassName = this.mClass.getSimpleName();
	}

	@Override
	protected void i(String tag, Object text)
	{
		if(super.isAll_Log_PrintConsole_Enable() && this.consoleIsAvailable)
		{
			console.print(ELogLevel.info_1, mClassName, tag, text);
		}
	}

	@Override
	protected void e(String tag, Object text, Exception exception,
			Class<? extends Exception> classT)
	{
		if(super.isAll_Log_PrintConsole_Enable() && this.consoleIsAvailable)
		{
			console.print(ELogLevel.error_2, mClassName, tag, text, exception,
					classT);
		}
	}

	@Override
	protected void w(String tag, Object text)
	{
		if(super.isAll_Log_PrintConsole_Enable() && this.consoleIsAvailable)
		{
			console.print(ELogLevel.warn_3, mClassName, tag, text);
		}
	}

	@Override
	protected void d(String tag, Object text)
	{
		if(super.isAll_Log_PrintConsole_Enable() && this.consoleIsAvailable)
		{
			console.print(ELogLevel.debug_4, mClassName, tag, text);
		}
	}

	@Override
	public void destroy()
	{

	}

	/**
	 * 设置客户端输出实例
	 * 
	 * @param console
	 *            the console to set
	 */
	private final void setConsole(ILocalEnviConsole console)
	{
		if(console != null)
		{
			this.consoleIsAvailable = true;// 为对象设置实例且标记可用状态
			this.console = console;
		}
		else
		{
			this.consoleIsAvailable = false;
		}
	}

}
