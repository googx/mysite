package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

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
 * @CreateDate 2018-04-12 下午1:19
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class PrintReqHandler extends DiscardServerHandler
{
	static MyLogger logger= MyLog.getLogger(PrintReqHandler.class, MyLogger.LogImplEnum.ConsoleLogImpl);
	
	PrintReqHandler(){
		logger.info("PrintReqHandler()==>对象被创建");
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		logger.info("PrintReqHandler=>channelRead()==>");

		if(msg instanceof ByteBuf){
			StringBuilder builder=new StringBuilder();
//			while (((ByteBuf) msg).isReadable()){
//				builder.append(((ByteBuf) msg).readCharSequence(1, CharsetUtil.UTF_8));
//				System.out.println("读取");
				builder.append(((ByteBuf) msg).toString(CharsetUtil.ISO_8859_1));
//				ctx.write(msg);
//				ctx.flush();

			ctx.writeAndFlush(msg);

//			}

//			boolean resu=((ByteBuf) msg).release();
			boolean resu=false;
			logger.info("channelRead()==>"+builder.toString()+",release:"+resu);

		}

	}



	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		super.handlerAdded(ctx);
		logger.info("handlerAdded()==>");
		
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
	{
		super.userEventTriggered(ctx, evt);
		logger.info("userEventTriggered()==>");
		
	}
}
