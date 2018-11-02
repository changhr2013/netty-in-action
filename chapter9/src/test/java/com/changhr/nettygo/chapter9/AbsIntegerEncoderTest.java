package com.changhr.nettygo.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbsIntegerEncoderTest {

    @Test
    public void testEncodede() {
        // 创建一个ByteBuf，并且写入9个负整数
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        // 创建EmbeddedChannel并安装一个要测试的AbsIntegerEncoder
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        // 写入ByteBuf，并断言调用readOutbound()方法将会产生数据
        assertTrue(channel.writeOutbound(buf));
        // 将该Channel标记为已完成状态
        assertTrue(channel.finish());

        // read bytes
        // 读取所产生的消息，并断言他们包含了对应的绝对值
        for (int i = 0; i < 10; i++) {
            assertEquals((Integer) i, channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}