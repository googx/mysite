package com.thesunboy.test.nio.netty.s01;/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-04-03 下午10:09
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

/**
 *
 * @author hanxu
 * @create 2018-04-03 下午10:09
 **/
public class MyDiscardHandler extends ChannelInboundHandlerAdapter
{
	MyLogger logger= MyLog.getLogger(MyDiscardHandler.class, MyLogger.LogImplEnum.ConsoleLogImpl);
	/**
	 * 通道可以读取时的方法,应该是每次有可用的数据时都会被调用的.
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		logger.info("channelRead()==>入站请求:"+msg.toString());
		if(msg instanceof ByteBuf)
		{
			ByteBuf byteBuf= (ByteBuf) msg;

			String msgstr=byteBuf.toString(CharsetUtil.UTF_8);
			logger.info("channelRead()==>"+msgstr);
			ReferenceCountUtil.release(byteBuf);

		}
//		if(msg instanceof ReferenceCounted){
//			ReferenceCounted referenceCounted= (ReferenceCounted) msg;
//			referenceCounted.release();
//		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		super.channelActive(ctx);
		logger.info("channelActive()==>");
		
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		super.channelInactive(ctx);
		logger.info("channelInactive()==>");
		
	}

	/**
	 * 见名知意,应该就是通道流中内容读完了..我猜测可能会进入关闭流程
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	{
				super.channelReadComplete(ctx);
		logger.info("channelReadComplete()==>请求完毕,"+ctx.toString());
		ctx.close();


	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		super.exceptionCaught(ctx, cause);
		RuntimeException runtimeException=new RuntimeException(cause);
		logger.error("exceptionCaught()==>DescriptionInfo",runtimeException,runtimeException.getClass());
	}
}
