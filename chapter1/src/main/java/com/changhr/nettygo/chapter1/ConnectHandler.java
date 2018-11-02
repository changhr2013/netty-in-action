package com.changhr.nettygo.chapter1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 通过回调触发ChannelHandler
 *
 * @author changhr
 * @create 2018-10-10 9:29
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当一个新的连接已经建立时，channelActive(ChannelHandlerContext)将会被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }
}
