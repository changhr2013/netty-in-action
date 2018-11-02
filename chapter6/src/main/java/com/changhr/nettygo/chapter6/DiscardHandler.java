package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 释放消息资源
 * 扩展了 ChannelInboundHandlerAdapter
 *
 * @author changhr
 * @create 2018-10-19 14:18
 */
@Sharable
public class DiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 丢弃已接收的消息
        ReferenceCountUtil.release(msg);
    }
}
