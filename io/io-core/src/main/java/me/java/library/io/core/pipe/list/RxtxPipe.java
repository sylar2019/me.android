package me.java.library.io.core.pipe.list;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.rxtx.RxtxChannel;
import io.netty.channel.rxtx.RxtxDeviceAddress;
import me.java.library.io.core.bus.list.RxtxBus;
import me.java.library.io.core.codec.list.RxtxCodec;
import me.java.library.io.core.pipe.AbstractPipe;

/**
 * File Name             :  RxtxPipe
 *
 * @Author :  sylar
 * @Create :  2019-10-05
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
public class RxtxPipe extends AbstractPipe<RxtxBus, RxtxCodec> {

    public RxtxPipe(RxtxBus bus, RxtxCodec codec) {
        super(bus, codec);
    }

    @Override
    protected void onStart() {
        super.onStart();

        group = new OioEventLoopGroup();
        try {
            RxtxDeviceAddress rxtxDeviceAddress = new RxtxDeviceAddress(bus.getRxtxPath());

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(RxtxChannel.class)
                    .handler(getChannelInitializer());

            ChannelFuture future = b.connect(rxtxDeviceAddress).sync();
            isRunning = true;
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            isRunning = false;
        }
    }

}