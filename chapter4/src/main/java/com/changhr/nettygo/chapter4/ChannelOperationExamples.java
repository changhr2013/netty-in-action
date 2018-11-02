package com.changhr.nettygo.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 写出到channel
 *
 * @author changhr
 * @create 2018-10-11 9:40
 */
public class ChannelOperationExamples {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    /** 写出到channel */
    public static void writingToChannel(){
        Channel channel = CHANNEL_FROM_SOMEWHERE;

        // 创建持有要写数据的ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);

        // 写数据并冲刷它
        ChannelFuture channelFuture = channel.writeAndFlush(buf);

        channelFuture.addListener((ChannelFutureListener) future -> {
            // 写操作完成，并且没有错误发生
            if(future.isSuccess()){
                System.out.println("Write successful");
            } else {
                // 记录错误
                System.err.println("Write error");
                future.cause().printStackTrace();
            }
        });

    }

    /** 从多个线程使用同一个channel */
    public static void writingToChannelFromManyThreads(){
        final Channel channel = CHANNEL_FROM_SOMEWHERE;

        // 创建要持有写数据的ByteBuf
        final ByteBuf buf = Unpooled.copiedBuffer("you data", CharsetUtil.UTF_8);

        // 创建将数据写到Channel的Runnable
        Runnable writer = () -> channel.write(buf.duplicate());

        // 获取到线程池Executor的引用
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 递交写任务给线程池以便在某个线程中执行
        // write in one thread
        executorService.execute(writer);

        // 递交另一个写任务以便在另一个线程中执行
        // write in another thread
        executorService.execute(writer);

    }


}
