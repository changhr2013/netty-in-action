package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 缓存到ChannelHandlerContext的引用
 *
 * @author changhr
 * @create 2018-10-19 15:43
 */
public class WriteHandler extends ChannelHandlerAdapter {

    private ChannelHandlerContext ctx;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 存储到ChannelHandlerContext的引用以供稍后使用
        this.ctx = ctx;
    }

    /**
     * 使用之前存储的到ChannelHandlerContext的引用来发送消息
     * @param msg
     */
    public void send(String msg) {
        ctx.writeAndFlush(msg);
    }
}
