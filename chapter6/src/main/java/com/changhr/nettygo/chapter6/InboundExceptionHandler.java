package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 基本的入站异常处理
 *
 * @author changhr
 * @create 2018-10-19 16:04
 */
public class InboundExceptionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常堆栈信息
        cause.printStackTrace();
        // 关闭channel
        ctx.close();
    }
}
