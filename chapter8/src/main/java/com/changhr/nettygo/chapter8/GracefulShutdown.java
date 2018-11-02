package com.changhr.nettygo.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * 优雅关闭
 *
 * @author changhr
 * @create 2018-10-25 9:45
 */
public class GracefulShutdown {

    public static void main(String[] args) {
        GracefulShutdown client = new GracefulShutdown();
        client.bootstrap();
    }

    private void bootstrap() {
        // 创建处理I/O的EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个Bootstrap类的实例并配置它
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                // ...
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        bootstrap.connect(new InetSocketAddress("www.manning.com", 80)).syncUninterruptibly();
        // ...
        // shutdownGracefully()方法将释放所有的资源，并且关闭所有的当前正在使用中的Channel
        Future<?> future = group.shutdownGracefully();
        // block until the group has shutdown
        future.syncUninterruptibly();
    }

}
