/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.nio;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b>
 * <p>
 * org.apache.commons.io.monitor 实现.
 * 通过看源码发现具体不是使用os的原生驱动机制.而是定时扫描磁盘的文件.这种效率低不采用.
 * 使用jdk提供的nio工具包,该工具包内部使用了os原生事件驱动方法
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 一些说明写这里
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * 一些说明写这里
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2018-1-6 - 下午3:11:44
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class FileWatch implements FileAlterationListener
{
	private final static MyLogger logger = MyLog.getLogger(FileWatch.class, LogImplEnum.ConsoleLogImpl);


	public static void main(String[] args)
	{
		FileWatch fileWatch = new FileWatch();

		// org.apache.commons.io.monitor.FileAlterationListener.class
		// org.apache.commons.io.monitor.FileAlterationListenerAdaptor.class
		// org.apache.commons.io.monitor.FileAlterationMonitor.class
		// org.apache.commons.io.monitor.FileAlterationObserver.class
		// org.apache.commons.io.monitor.FileEntry.class

		FileAlterationMonitor monitor = new FileAlterationMonitor(10);
		File directory = new File("F:/Project_Management/Personal");
		FileAlterationObserver observer = new FileAlterationObserver(directory);
		observer.addListener(fileWatch);
		// observer.checkAndNotify();//立刻检查一次,有变化这会发出一次通知吧.应该是响应用户操作用的
		monitor.addObserver(observer);
		try
		{
			monitor.start();
		}
		catch (Exception e)
		{
			// TODO hx940929 by 下午4:31:06
			logger.error("请描述异常信息", e, e.getClass());
		}

	}



	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param observer
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onStart(FileAlterationObserver observer)
	{
	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param directory
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onDirectoryCreate(File directory)
	{
		logger.info("onDirectoryCreate()==>" + directory);
	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param directory
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onDirectoryChange(File directory)
	{
		// TODO hx940929 by 下午3:48:48
		logger.warn("FileAlterationListener=>onDirectoryChange()=>" + directory);

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param directory
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onDirectoryDelete(File directory)
	{
		// TODO hx940929 by 下午3:48:48
		logger.warn("FileAlterationListener=>onDirectoryDelete()=>" + directory);

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param file
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onFileCreate(File file)
	{
		logger.info("onFileCreate()==>" + file.toString());
	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param file
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onFileChange(File file)
	{
		// TODO hx940929 by 下午3:48:48
		logger.warn("FileAlterationListener=>onFileChange()=>" + file);

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param file
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onFileDelete(File file)
	{
		// TODO hx940929 by 下午3:48:48
		logger.warn("FileAlterationListener=>onFileDelete()=>" + file);

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param observer
	 * @Author hx940929
	 * @Date 2018-1-6 - 下午3:48:48
	 */
	@Override
	public void onStop(FileAlterationObserver observer)
	{

	}
}
