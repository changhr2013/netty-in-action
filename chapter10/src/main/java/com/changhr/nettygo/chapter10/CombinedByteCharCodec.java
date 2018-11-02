package com.changhr.nettygo.chapter10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 10.10 CombinedChannelDuplexHandler<I, O>
 *
 * @author changhr
 * @create 2018-11-01 11:38
 * 通过该解码器和编码器实现参数话CombinedByteCharCodec
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec(){
        // 将委托实例传递给父类
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
