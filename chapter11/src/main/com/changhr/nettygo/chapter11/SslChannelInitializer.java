package com.changhr.nettygo.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 添加SSL/TLS支持
 *
 * @author changhr
 * @create 2018-11-01 14:13
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;

    /**
     * 构造方法
     * @param context   传入要使用的SslContext
     * @param startTls  如果设置为true，第一个写入的消息将不会被加密（客户端应该设置为true）
     */
    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        // 对于每个SslHandler实例，都是用Channel的ByteBufAllocator从SslContext获取一个新的SSLEngine
        SSLEngine engine = context.newEngine(channel.alloc());
        // 将SslHandler作为第一个ChannelHandler添加到ChannelPipeline中
        channel.pipeline().addFirst("ssl", new SslHandler(engine, startTls));
    }
}
