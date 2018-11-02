package com.changhr.nettygo.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导一个客户端
 *
 * @author changhr
 * @create 2018-10-22 15:10
 */
public class BootstrapClient {

    public static void main(String[] args) {
        BootstrapClient client = new BootstrapClient();
        client.bootstrap();
    }

    public void bootstrap(){
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个BootStrap类的实例以创建和连接新的客户端Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，提供用于处理Channel事件的EventLoop
        bootstrap.group(group)
                // 指定要使用的channel实现
                .channel(NioSocketChannel.class)
                // 设置用于Channel事件和数据的ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
                            throws Exception {
                        System.out.println("Received data");
                    }
                });
        // 连接到远程主机
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if(channelFuture.isSuccess()){
                System.out.println("Connection established");
            } else {
                System.err.println("Connection attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }

}
