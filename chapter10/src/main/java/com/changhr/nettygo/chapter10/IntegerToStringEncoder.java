package com.changhr.nettygo.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 10.6 IntegerToStringEncoder
 *
 * @author changhr
 * @create 2018-11-01 10:31
 * 扩展了MessageToMessageEncoder
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer msg, List<Object> out) throws Exception {
        // 将Integer转换为String，并将其添加到List中
        out.add(String.valueOf(msg));
    }
}
