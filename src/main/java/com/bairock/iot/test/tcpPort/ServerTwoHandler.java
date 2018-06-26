package com.bairock.iot.test.tcpPort;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerTwoHandler extends ChannelInboundHandlerAdapter{
	public static ChannelHandlerContext channel;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		if(null != channel) {
			channel.close();
		}
		channel = ctx;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf)msg;
		try{
			byte[] req = new byte[m.readableBytes()];
			m.readBytes(req);
			//String str = new String(req, "GBK");
			ServerOneHandler.channel.writeAndFlush(Unpooled.copiedBuffer(req));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			m.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		ctx.close();
	}
}
