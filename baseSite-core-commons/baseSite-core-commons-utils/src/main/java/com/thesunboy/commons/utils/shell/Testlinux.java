/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.commons.utils.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
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
 * @CreateDate 2017-7-8 - 下午6:26:10 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Testlinux
{
	private final static MyLogger logger = MyLogFactory.getLogger(Testlinux.class, LogImplEnum.ConsoleLogImpl);
	private Channel mChannel;
	private Session mSession;
	public static void main(String[] args) throws Exception, Throwable
	{
		Runtime runtime= Runtime.getRuntime();
		
		Process process=runtime.exec("");
		// process.
	}

	private <T extends Channel> T openChannel(ChannelType type)
	{
		if(this.mSession != null && this.mSession.isConnected())
		{
			try
			{
				this.mChannel = this.mSession.openChannel(type.getTypeValue());
				return (T) this.mChannel;
			}
			catch (JSchException e)
			{
				// TODO hx940929 by 下午7:20:37
				logger.error("请描述异常信息", e, e.getClass());
			}
		}
		return null;
	}

	private void main_jsch() throws Exception
	{

		Testlinux linux=new Testlinux();

		Runtime runtime = Runtime.getRuntime();
		// runtime.exec
		Session session=linux.getSSHSession();
		final ChannelExec shell = linux.openChannel(ChannelType.Exec);
		shell.connect();
		new Thread(new Runnable()
		{
			BufferedReader reader;

			@Override
			public void run()
			{
				try
				{
					reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
				}
				catch (IOException e1)
				{
					// TODO hx940929 by 下午7:49:13
					logger.error("请描述异常信息", e1, e1.getClass());
					Thread.currentThread().interrupt();
				}
				while (Thread.currentThread().isInterrupted() == false)
				{
					logger.info("run()==>等待命令执行");
					StringBuilder resu = new StringBuilder();
					String tem;
					try
					{
						while ((tem = reader.readLine()) != null)
						{
							resu.append(tem);
						}
						logger.info("run()==>执行命令结果: " + resu);
					}
					catch (IOException e)
					{
						// TODO hx940929 by 下午7:48:05
						logger.error("请描述异常信息", e, e.getClass());
					}
				}
				logger.info("run()==>输出线程退出");
			}
		}).start();
  
		String tem;
		try
		{
			BufferedReader	reader = new BufferedReader(new InputStreamReader(System.in));
			Writer writer=new OutputStreamWriter(shell.getOutputStream());
			while ((tem = reader.readLine()) != null)
			{
			}
		}
		catch (Exception exception)
		{
			logger.error("main=>ErrorCause", exception, exception.getClass());
		}


	}

	private Session getSSHSession() throws UnknownHostException, IOException
	{
		Socket socket = new Socket("10.193.2.1", 1113);
		logger.info("getSSHSession()==>" + socket.isConnected());
		JSch clinetJSch = new JSch();
		try
		{
			Session session = clinetJSch.getSession("root", "10.193.2.1", 1113);
			try
			{
				session.setPassword("Hxadmin19940929");
				session.setConfig("StrictHostKeyChecking", "no");
				// session.setTimeout(30);// 30秒登录超时时间
			}
			catch (Exception e)
			{
				logger.error("请描述异常信息", e, e.getClass());
			}
			session.connect();
			return session.isConnected() ? (this.mSession = session) : null;
		}
		catch (JSchException e)
		{
			// TODO hx940929 by 下午6:28:22
			logger.error("请描述异常信息", e, e.getClass());
			return null;
		}

	}
}
