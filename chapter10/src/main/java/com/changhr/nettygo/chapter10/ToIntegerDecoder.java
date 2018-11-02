package com.changhr.nettygo.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 10.1 ToIntegerDecoder类扩展了ByteToMessageDecoder
 *
 * @author changhr
 * @create 2018-10-25 16:23
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {

    /** 扩展ByteToMessageDecoder类，以将字节解码为特定的格式 */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf input, List<Object> out) throws Exception {

        // 检查是否至少有四个字节可读（一个int的字节长度）
        while (input.readableBytes() >= 4){
            // 从入站ByteBuf中读取一个int，并将其添加到解码消息中
            out.add(input.readInt());
        }
    }
}
