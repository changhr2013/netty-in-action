package com.changhr.nettygo.chapter2.echoclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Echo 客户端
 * 1. 连接到服务器
 * 2. 发送一个或多个消息
 * 3. 对于每个消息，等待并接收从服务器发回的相同的消息
 * 4. 关闭连接
 * @author changhr
 * @create 2018-10-10 10:43
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            // 创建Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            // 指定EventLoopGroup以处理客户端事件，需要适用于NIO的实现
            bootstrap.group(group)
                    // 适用于NIO传输的Channel类型
                    .channel(NioSocketChannel.class)
                    // 设置服务器的InetSocketAddress
                    .remoteAddress(host, port)
                    // 在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture future = bootstrap.connect().sync();
            // 阻塞，直到 Channel 关闭
            future.channel().closeFuture().sync();

        }finally {
            // 关闭线程池并且释放所有的资源
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() + " <host><port>"
            );
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
}
