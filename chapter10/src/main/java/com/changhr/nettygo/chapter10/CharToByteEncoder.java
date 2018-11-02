package com.changhr.nettygo.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 10.9 charToByteEncoder类
 *
 * @author changhr
 * @create 2018-11-01 11:34
 * 扩展了MessageToByteEncoder
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Character msg, ByteBuf out)
            throws Exception {
        // 将Character解码为char，并将其写入到出站ByteBuf中
        out.writeChar(msg);
    }
}
