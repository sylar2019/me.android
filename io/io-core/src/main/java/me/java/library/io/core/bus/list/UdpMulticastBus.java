package me.java.library.io.core.bus.list;

import me.java.library.io.core.bus.AbstractSocketBus;
import me.java.library.io.core.bus.BusType;

/**
 * File Name             :  TcpServerBus
 *
 * @Author :  sylar
 * @Create :  2019-10-15
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
public class UdpMulticastBus extends AbstractSocketBus {

    @Override
    public BusType getBusType() {
        return BusType.UdpMulticast;
    }


    public String getNetworkInterfaceName() {
        return getOrDefault(RxtxBus.BUS_ATTR_SOCKET_NETWORK_INTERFACE, "en1");
    }
}