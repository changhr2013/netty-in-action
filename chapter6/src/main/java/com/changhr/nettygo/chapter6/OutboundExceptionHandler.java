package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 添加ChannelFutureListener到ChannelPromise
 *
 * @author changhr
 * @create 2018-10-19 16:24
 */
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        promise.addListener((ChannelFutureListener)(futureListener) -> {
            if(!futureListener.isSuccess()){
                futureListener.cause().printStackTrace();
                futureListener.channel().close();
            }
        });
    }
}
