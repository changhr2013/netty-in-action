package com.changhr.nettygo.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 在服务端支持WebSocket
 *
 * @author changhr
 * @create 2018-11-01 15:25
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(
                new HttpServerCodec(),
                // 为握手提供聚合的HttpRequest
                new HttpObjectAggregator(64 * 1024),
                // 如果被请求的端点是"/websocket",则处理该升级握手
                new WebSocketServerProtocolHandler("/websocket"),
                // TextFrameHandler处理TextWebSocketFrame
                new TextFrameHandler(),
                // BinaryFrameHandler处理BinaryWebSocketFrame
                new BinaryFrameHandler(),
                // ContinuationFrameHandler处理ContinuationWebSocketFrame
                new ContinuationFrameHandler()
        );

    }

    public static final class TextFrameHandler
            extends SimpleChannelInboundHandler<TextWebSocketFrame> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                    TextWebSocketFrame textWebSocketFrame) throws Exception {
            // Handle text frame
        }
    }

    public static final class BinaryFrameHandler
            extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                    BinaryWebSocketFrame binaryWebSocketFrame) throws Exception {
            // Handle binary frame
        }
    }

    public static final class ContinuationFrameHandler
            extends SimpleChannelInboundHandler<ContinuationWebSocketFrame>{

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                    ContinuationWebSocketFrame continuationWebSocketFrame) throws Exception {
            // Handle continuation frame
        }
    }
}
