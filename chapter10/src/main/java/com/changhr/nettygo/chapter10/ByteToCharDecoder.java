package com.changhr.nettygo.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 10.8 ByteToCharDecoder类
 *
 * @author changhr
 * @create 2018-11-01 11:30
 */
public class ByteToCharDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        // 将一个或者多个Character对象添加到传出的List中
        while (in.readableBytes() >= 2){
            out.add(in.readChar());
        }
    }
}
