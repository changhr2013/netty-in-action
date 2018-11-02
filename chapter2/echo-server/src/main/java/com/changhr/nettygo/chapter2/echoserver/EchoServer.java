package com.changhr.nettygo.chapter2.echoserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Echo 引导服务器
 * 1. 绑定到服务器将在其上监听并接受传入连接请求的端口
 * 2. 配置Channel，以将有关的入站消息通知给 EchoServerHandler 实例
 * @author changhr
 * @create 2018-10-10 10:49
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1){
            System.err.println(
                    "Usage: " + EchoServer.class.getSimpleName() + " <port>");
        }
        int port = Integer.parseInt(args[0]);
        // 调用服务器的start()方法
        new EchoServer(port).start();
    }

    private void start() throws Exception {

        final EchoServerHandler serverHandler = new EchoServerHandler();

        // 创建EventLoopGroup，来接受和处理新的连接
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            // 创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group)
                    // 指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                    // 当一个新的连接 被接受时，一个新的子 Channel 将会被创建，
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {

                            // EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });

            // 异步地绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();

            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully();
        }
    }

}
