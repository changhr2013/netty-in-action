package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 使用SimpleChannelInboundHandler
 * 扩展了SimpleChannelInboundHandler
 *
 * @author changhr
 * @create 2018-10-19 14:22
 */
@Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        // 不需要任何显式的资源释放
        // No need to do anything special
    }
}
