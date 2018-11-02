package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 消费并释放入站消息
 *
 * @author changhr
 * @create 2018-10-19 14:32
 */
@Sharable
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 通过调用ReferenceCountUtil.release()释放资源
        ReferenceCountUtil.release(msg);
    }
}
