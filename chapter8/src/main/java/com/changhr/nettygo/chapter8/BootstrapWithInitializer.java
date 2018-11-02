package com.changhr.nettygo.chapter8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.InetSocketAddress;

/**
 * 引导和使用ChannelInitializer
 *
 * @author changhr
 * @create 2018-10-24 16:02
 */
public class BootstrapWithInitializer {

    public void bootstrap() throws InterruptedException {
        // 创建ServerBootstrap以创建和绑定新的Channel
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置EventLoopGroup，并将提供用以处理Channel事件的EventLoop
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 指定Channel的实现
                .channel(NioServerSocketChannel.class)
                // 注册一个CHannelInitializerImpl的实例来设置ChannelPipeline
                .childHandler(new ChannelInitializerImpl());

        // 绑定到地址
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.sync();
    }

    /**
     * 用以设置ChannelPipeline的自定义CHannelInitializerImpl实现
     */
    final class ChannelInitializerImpl extends ChannelInitializer<Channel> {

        /** 将所需的ChannelHandler添加到ChannelPipeline */
        @Override
        protected void initChannel(Channel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }

}
