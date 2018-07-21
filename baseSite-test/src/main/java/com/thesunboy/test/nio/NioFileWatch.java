/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.nio;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;
import net.contentobjects.jnotify.IJNotify;
import net.contentobjects.jnotify.JNotify;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>功能说明:</b>
 * <p>
 * 一些说明写这里
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
 * @CreateDate 2018-1-6 - 下午5:07:17
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class NioFileWatch
{
	private final static MyLogger logger = MyLogFactory.getLogger(NioFileWatch.class, LogImplEnum.ConsoleLogImpl);

	public static void main(String[] args)
	{

		NioFileWatch watch = new NioFileWatch();
		try
		{
//			watch.jnotify(args);
			watch.jdkNio();
		}
		catch (Exception e)
		{
			// TODO hx940929 by 下午9:24:52
			logger.error("请描述异常信息", e, e.getClass());
		}
	}


	void jdkNio()
	{

		try
		{
			// WatchService fileWatchService = FileSystems.getFileSystem(new
			// URI("E:/KuGou")).newWatchService();
			String testfileDir="/home/hanxu/ideaProject/mySubversion/mysite/baseSite-test/target/nioTestFolder";

			File testfileDir_File = new File(testfileDir);
//			logger.info("jdkNio()==>isFile" + searchaaaDirFile.isFile() + " , isDIr" + searchaaaDirFile.isDirectory());
//			logger.info("jdkNio()==>isFile" + tempfile.isFile() + " , isDIr" + tempfile.isDirectory());
			FileSystem fileSystem = FileSystems.getDefault();
			// Path path = fileSystem.getPath(searchDirFile.getPath());ss
			WatchService fileWatchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(testfileDir);
			Kind[] eventType = new Kind[] { StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY ,StandardWatchEventKinds.OVERFLOW};


			WatchKey watchKey = path.register(fileWatchService, eventType);
			do
			{

//				RejectedExecutionHandler
				watchKey = fileWatchService.take();
				List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
				for (WatchEvent<?> ievet : watchEvents)
				{

					Kind kind = ievet.kind();
					Path eventPath = (Path) ievet.context();
					Path resolve = path.resolve(eventPath);
					File file = new File(eventPath.toUri());
					Path path2 = eventPath.getFileName();
					Path path3 = eventPath.toAbsolutePath();
					Path path4 = eventPath.getParent();
					logger.info("jdkNio()==>isFile" + file.isFile() + " , isDIr" + file.isDirectory() + "  getPath:" + file.getPath());
				
					if(file.isDirectory())
					{
						fileSystem.getPath(file.toString()).register(fileWatchService, eventType);
						logger.info("jdkNio()==>是目录,注册监听" + file.toString());// RELATIVE//RELATIVE
					}
					String pathString = file.getAbsolutePath();

					logger.info("main()==>" + kind + ".path=" + pathString + ", isa" + file.isHidden());
				}
			}
			while (watchKey.reset());
		}
		catch (IOException e)
		{
			// TODO hx940929 by 下午3:13:46
			logger.error("请描述异常信息", e, e.getClass());
		}
		catch (InterruptedException e)
		{
			// TODO hx940929 by 下午3:20:01
			logger.error("请描述异常信息", e, e.getClass());
		}

	}


	private void jnotify(String[] args)
	{
		try
		{
			System.out.println(System.getProperty("java.library.path"));
			// args = new String[] { "E:/KuGou" };
			JNotify.main(args);
		}
		catch (InterruptedException e)
		{
			// TODO hx940929 by 下午11:02:49
			logger.error("请描述异常信息", e, e.getClass());
		}
		catch (IOException e)
		{
			// TODO hx940929 by 下午11:02:49
			logger.error("请描述异常信息", e, e.getClass());
		}
		//net.contentobjects.jnotify.win32.JNotify_win32.class//jni文件.// TODO 测试完ok后自己重构它的代码.
		// net.contentobjects.jnotify.JNotify jNotify;// main
		IJNotify notify;
		JNotify nJNotify;
	}

	public void WatchService() throws Exception
	{
		String filePath = ("E:");
		AtomicInteger count = new AtomicInteger();
		// 获取文件系统的WatchService对象
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(filePath);
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

		File file = new File(filePath);
		ArrayList<File> fList = new ArrayList<File>();
		fList.add(file);
		while (fList.size() > 0)
		{
			File f = fList.remove(0);
			if(f.listFiles() == null) continue;
			for (File file2 : f.listFiles())
			{
				if(file2.isDirectory())
				{// 下一级目录
					fList.add(file2);
					logger.info("name()==>register" + file2.toString());
					// 依次注册子目录
					try
					{
						Paths.get(file2.getAbsolutePath()).register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
						count.getAndIncrement();
					}
					catch (java.nio.file.AccessDeniedException e)
					{
						logger.info("name()==>AccessDeniedException" + file2);
					}

				}
			}
		}
		logger.info("name()==>" + count.get());
		while (true)
		{
			// 获取下一个文件改动事件
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents())
			{
				Kind kind = event.kind();
				Path eventPath = (Path) event.context();
				Path resolve = path.resolve(eventPath);
				File file2 = resolve.toFile();
				logger.info("jdkNio()==>[" + kind + "]  isFile" + file2.isFile() + " , isDIr" + file2.isDirectory() + "  getPath:" + file2.getPath());

			}
			// 重设WatchKey
			boolean valid = key.reset();
			// 如果重设失败，退出监听
			if(!valid)
			{
				break;
			}
		}
	}
}
