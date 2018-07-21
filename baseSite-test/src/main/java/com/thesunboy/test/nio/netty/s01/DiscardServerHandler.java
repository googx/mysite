package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

/**
 * <b>功能说明:</b><p>
 * 连接入站 handler.类似于j2ee里面的filter功能.可以做流量限制和防火墙.做些通用的包装处理等等.
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-04-02 上午11:16
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter
{
	MyLogger logger= MyLog.getLogger(DiscardServerHandler.class, MyLogger.LogImplEnum.ConsoleLogImpl);


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
			ReferenceCountUtil.release(byteBuf);
		}else if(msg instanceof ReferenceCounted){
			ReferenceCounted referenceCounted= (ReferenceCounted) msg;
			referenceCounted.release();
		}

	}

	/**
	 * 见名知意,应该就是通道流中内容读完了..我猜测可能会进入关闭流程
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	{
//		super.channelReadComplete(ctx);
		logger.info("channelReadComplete()==>请求完毕,"+ctx.toString());
		ctx.close();


	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
//		super.exceptionCaught(ctx, cause);
		logger.error("exceptionCaught()==>DescriptionInfo",new RuntimeException(cause), RuntimeException.class);
	}
}
