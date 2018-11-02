package com.changhr.nettygo.chapter6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @author changhr
 * @create 2018-10-19 16:11
 */
public class ChannelFutures {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final ByteBuf SOME_MSG_FROM_SOMEWHERE = Unpooled.buffer(1024);

    /**
     * 添加ChannelFutureListener 到 ChannelFuture
     */
    public static void addingChannelFutureListener() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ByteBuf someMessage = SOME_MSG_FROM_SOMEWHERE;
        // ...
        ChannelFuture future = channel.write(someMessage);
        future.addListener((ChannelFutureListener)(futureListener) ->{
            if(!futureListener.isSuccess()){
                futureListener.cause().printStackTrace();
                futureListener.channel().close();
            }
        });
    }
}
