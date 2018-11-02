package com.changhr.nettygo.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 10.3 IntegerToMessageDecoder类
 *
 * @author changhr
 * @create 2018-10-25 16:36
 * 扩展了MessageToMessageEncoder<Integer>
 */
public class IntegerToStringDecoder extends MessageToMessageEncoder<Integer> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list)
            throws Exception {
        // 将Integer消息转换为它的String表示，并将其添加到输出的List中
        list.add(String.valueOf(integer));
    }
}
