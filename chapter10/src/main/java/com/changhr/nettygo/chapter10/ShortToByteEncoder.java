package com.changhr.nettygo.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 10.5 ShortToByteEncoder类
 *
 * @author changhr
 * @create 2018-11-01 10:19
 * 扩展了MessageToByteEncoder
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Short msg, ByteBuf out)
            throws Exception {
        // 将short写入ByteBuf中
        out.writeShort(msg);
    }
}
