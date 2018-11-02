package com.changhr.nettygo.chapter6;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import static io.netty.channel.DummyChannelPipeline.DUMMY_INSTANCE;

/**
 * 修改ChannelPipeline
 *
 * @author changhr
 * @create 2018-10-19 14:51
 */
public class ModifyChannelPipeline {

    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DUMMY_INSTANCE;

    public static void modifyPipeline(){
        ChannelPipeline pipeline = CHANNEL_PIPELINE_FROM_SOMEWHERE;
        FirstHandler firstHandler = new FirstHandler();
        // 将该实例作为handler1添加到ChannelPipeline中
        pipeline.addLast("handler1", firstHandler);
        // 将一个SecondHandler的实例作为"handler2"添加到ChannelPipeline的第一个槽中。
        // 这意味着它将被放置在已有的"handler1"之前
        pipeline.addFirst("handler2", new SecondHandler());
        // 将以个ThirdHandler的实例作为"handler3"添加到ChannelPipeline的最后一个槽中
        pipeline.addLast("handler3", new ThirdHandler());
        // ...
        // 通过名称移除handler3
        pipeline.remove("handler3");
        // 通过引用移除FirstHandler(它是唯一的，所以不需要它的名称)
        pipeline.remove(firstHandler);
        // 将SecondHandler("handler2")替换为FourthHandler:"handler4"
        pipeline.replace("handler2", "handler4", new FourthHandler());

    }


    private static final class FirstHandler extends ChannelHandlerAdapter {}
    private static final class SecondHandler extends ChannelHandlerAdapter {}
    private static final class ThirdHandler extends ChannelHandlerAdapter {}
    private static final class FourthHandler extends ChannelHandlerAdapter {}



}
