package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * 丢弃并释放出站消息
 *
 * @author changhr
 * @create 2018-10-19 14:37
 */
@Sharable
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        // 通过使用ReferenceCountUtil.release(...)方法释放资源
        ReferenceCountUtil.release(msg);
        // 通知ChannelPromise数据已经被处理了
        promise.setSuccess();
    }
}
