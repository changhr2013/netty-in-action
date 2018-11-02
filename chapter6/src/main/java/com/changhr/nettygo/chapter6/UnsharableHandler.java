package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author changhr
 * @Sharable的错误用法
 * @create 2018-10-19 15:54
 */
@Sharable
public class UnsharableHandler extends ChannelInboundHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 将count字段值 +1
        count ++;
        System.out.println("channelRead(...) called the " + count + " time.");
        // 记录方法调用，并转发给下一个ChannelHandler
        ctx.fireChannelRead(msg);
    }
}
