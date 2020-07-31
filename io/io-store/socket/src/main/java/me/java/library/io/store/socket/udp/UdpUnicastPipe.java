package me.java.library.io.store.socket.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import me.java.library.io.core.pipe.AbstractPipe;

/**
 * File Name             :  UdpUnicastPipe
 *
 * @author :  sylar
 * Create :  2019-10-05
 * Description           :  UDP单播
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public class UdpUnicastPipe extends AbstractPipe<UdpUnicastBus, UdpCodec> {
    public UdpUnicastPipe(UdpUnicastBus bus, UdpCodec codec) {
        super(bus, codec);
    }

    @Override
    protected boolean onStart() throws Exception {
        masterLoop = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(masterLoop)
                .channel(NioDatagramChannel.class)
                .handler(channelInitializer);

        return bind(bootstrap, bus.getHost(), bus.getPort()).sync().isDone();
    }


}
