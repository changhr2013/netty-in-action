package com.changhr.nettygo.chapter6;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DummyChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

import static io.netty.channel.DummyChannelHandlerContext.DUMMY_INSTANCE;

/**
 * 使用ChannelHandlerContext
 *
 * @author changhr
 * @create 2018-10-19 15:20
 */
public class WriteHandlers {

    private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DUMMY_INSTANCE;
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DummyChannelPipeline.DUMMY_INSTANCE;

    /**
     * 从 ChannelHandlerContext 访问 Channel
     */
    public static void writeViaChannel() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // 获取到与ChannelHandlerContext相关联的Channel的引用
        Channel channel = ctx.channel();
        // 通过Channel写入缓冲区
        channel.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

    /**
     * 通过 ChannelHandlerContext 访问 ChannelPipeline
     */
    public static void writeViaChannelPipeline() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        // 获取到与ChannelHandlerContext相关联的ChannelPipeline的引用
        ChannelPipeline pipeline = ctx.pipeline();
        // 通过ChannelPipeline写入缓冲区
        pipeline.write(Unpooled.copiedBuffer("Netty in Action", Charset.forName("UTF-8")));
    }

    /**
     * 调用 ChannelHandlerContext 的 write() 方法
     */
    public static void writeViaChannelHandlerContext() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE;
        ctx.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }
}
