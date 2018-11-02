package io.netty.channel;

/**
 * Pipeline
 *
 * @author changhr
 * @create 2018-10-19 12:00
 */
public class DummyChannelPipeline extends DefaultChannelPipeline {

    public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipeline(null);

    public DummyChannelPipeline(Channel channel) {
        super(channel);
    }
}
