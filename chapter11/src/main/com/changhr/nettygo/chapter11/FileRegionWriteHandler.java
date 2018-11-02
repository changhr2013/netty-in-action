package com.changhr.nettygo.chapter11;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

/**
 * 使用FileRegion传输文件的内容
 *
 * @author changhr
 * @create 2018-11-02 11:32
 */
public class FileRegionWriteHandler extends ChannelInboundHandlerAdapter {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final File FILE_FROM_SOMEWHERE = new File("");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        File file = FILE_FROM_SOMEWHERE;
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        //...
        // 创建一个FileInputStream
        FileInputStream in = new FileInputStream(file);
        // 以该文件的完整长度创建一个新的DefaultFileRegion
        DefaultFileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());

        // 发送该DefaultFileRegion，并注册一个ChannelFutureListener
        channel.writeAndFlush(region).addListener(
                new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        // 处理失败
                        if (!channelFuture.isSuccess()){
                            Throwable cause = channelFuture.cause();
                            // Do something
                        }
                    }
                }
        );
    }
}
