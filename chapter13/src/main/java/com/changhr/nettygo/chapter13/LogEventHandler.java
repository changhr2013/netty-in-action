package com.changhr.nettygo.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * LogEventHandler
 *
 * @author changhr
 * @create 2018-11-16 11:26
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {

        // 创建StringBuilder，并且构建输出的字符串
        StringBuilder builder = new StringBuilder();
        builder.append(msg.getReceivedTimestamp());
        builder.append(" [");
        builder.append(msg.getSource().toString());
        builder.append("] [");
        builder.append(msg.getLogfile());
        builder.append("] : ");
        builder.append(msg.getMsg());
        // 打印 LogEvent 的数据
        System.out.println(builder.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 当异常发生时，打印栈跟踪信息，并关闭对应的Channel
        cause.printStackTrace();
        ctx.close();
    }
}
