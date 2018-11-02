package io.netty.channel;

import io.netty.util.concurrent.EventExecutor;

/**
 * Context
 *
 * @author changhr
 * @create 2018-10-19 12:02
 */
public class DummyChannelHandlerContext extends AbstractChannelHandlerContext{

    public static ChannelHandlerContext DUMMY_INSTANCE = new DummyChannelHandlerContext(
            null,
            null,
            null,
            true,
            true
    );

    public DummyChannelHandlerContext(DefaultChannelPipeline pipeline,
                               EventExecutor executor,
                               String name, boolean inbound, boolean outbound) {
        super(pipeline, executor, name, inbound, outbound);
    }

    @Override
    public ChannelHandler handler() {
        return null;
    }
}
