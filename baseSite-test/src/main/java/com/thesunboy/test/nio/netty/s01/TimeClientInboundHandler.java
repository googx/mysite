package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-04-14 下午6:10
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TimeClientInboundHandler extends ChannelInboundHandlerAdapter
{
	MyLogger logger= MyLog.getLogger(TimeClientInboundHandler.class, MyLogger.LogImplEnum.ConsoleLogImpl);
	OutputStream outputStream;
	TimeClientInboundHandler(){

		try
		{
			outputStream=new FileOutputStream(new File("/media/hanxu/d_ext4/copy.tar.gz")) ;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}



	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
//		super.channelRead(ctx, msg);

		logger.info("channelRead()==>"+Thread.currentThread().getName()+",id:"+Thread.currentThread().getId());
		if(msg instanceof ByteBuf){
			String timeresu="";//((ByteBuf) msg).toString(CharsetUtil.UTF_8);
//			long time = ((ByteBuf) msg).readLong();

			try
			{
				((ByteBuf) msg).readBytes(outputStream,((ByteBuf) msg).capacity() );
				//			logger.info("channelRead()==>"+timeresu+", time:"+time);

			}
			finally
			{	outputStream.flush();
				outputStream.close();

			}

		}

		if(msg instanceof ReferenceCounted){
			ReferenceCountUtil.release(msg);
		}

	}
}
