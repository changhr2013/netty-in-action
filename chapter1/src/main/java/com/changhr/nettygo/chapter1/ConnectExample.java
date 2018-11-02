package com.changhr.nettygo.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 连接示例
 *
 * @author changhr
 * @create 2018-10-10 9:36
 */
public class ConnectExample {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect(){
        Channel channel = CHANNEL_FROM_SOMEWHERE;

        // 展示一个ChannelFuture作为一个I/O操作的一部分返回的例子
        // 异步的连接到远程节点
        ChannelFuture future = channel.connect(
                new InetSocketAddress("192.168.40.60", 25));

        // 展示如何利用ChannelFutureListener
        // 注册一个ChannelFutureListener，以便在操作完成时获得通知
        future.addListener((ChannelFutureListener)(channelFuture)->{

            // 检查操作状态
            if(channelFuture.isSuccess()){

                // 如果操作时成功的，则创建一个ByteBuf以持有数据
                ByteBuf buffer = Unpooled.copiedBuffer(
                        "hello", Charset.defaultCharset());

                // 将数据异步的发送到远程节点，返回一个ChannelFuture
                ChannelFuture wf = channelFuture.channel()
                        .writeAndFlush(buffer);
            } else {

                // 如果发生错误，则访问描述原因的Throwable
                Throwable cause = channelFuture.cause();
                cause.printStackTrace();
            }
        });
    }

}
