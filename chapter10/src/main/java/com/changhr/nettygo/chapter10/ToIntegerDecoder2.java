package com.changhr.nettygo.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 10.2 ToIntegerDecoder2扩展了ReplayingDecoder
 *
 * @author changhr
 * @create 2018-10-25 16:29
 * 扩展ReplayingDecoder<Void>以将字节解码为消息
 */
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {

    /**
     * 传入的ByteBuf是ReplayingDecoderByteBuf
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(byteBuf.readInt());
    }
}
