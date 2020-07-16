package me.java.library.io.store.rxtx;

import me.java.library.io.common.bus.AbstractBus;
import me.java.library.io.common.bus.BusType;

/**
 * File Name             :  RxtxBus
 *
 * @author :  sylar
 * Create :  2019-10-15
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
public class RxtxBus extends AbstractBus {
    final static String BUS_ATTR_RXTX_PATH = "path";
    final static String BUS_ATTR_RXTX_BAUDRATE = "baudrate";
    final static String BUS_ATTR_RXTX_STOPBITS = "stopbits";
    final static String BUS_ATTR_RXTX_DATABITS = "Databits";
    final static String BUS_ATTR_RXTX_PARITYBIT = "Paritybit";
    final static String BUS_ATTR_RXTX_DTR = "DTR";
    final static String BUS_ATTR_RXTX_RTS = "RTS";

    @Override
    public BusType getBusType() {
        return BusType.RXTX;
    }

    public String getRxtxPath() {
        return getOrDefault(BUS_ATTR_RXTX_PATH, "/dev/ttyMT1");
    }

    public void setRxtxPath(String rxtxPath) {
        setAttr(BUS_ATTR_RXTX_PATH, rxtxPath);
    }

    public int getRxtxBaud() {
        return getOrDefault(BUS_ATTR_RXTX_BAUDRATE, 9600);
    }

    public void setRxtxBaud(int baud) {
        setAttr(BUS_ATTR_RXTX_BAUDRATE, baud);
    }

    public int getRxtxDatabits() {
        return getOrDefault(BUS_ATTR_RXTX_DATABITS, 8);
    }

    public void setRxtxDatabits(int databits) {
        setAttr(BUS_ATTR_RXTX_DATABITS, databits);
    }

    public int getRxtxStopbits() {
        return getOrDefault(BUS_ATTR_RXTX_STOPBITS, 1);
    }

    public void setRxtxStopbits(int stopbits) {
        setAttr(BUS_ATTR_RXTX_STOPBITS, stopbits);
    }

    public int getRxtxParitybit() {
        return getOrDefault(BUS_ATTR_RXTX_PARITYBIT, 0);
    }

    public void setRxtxParitybit(int paritybit) {
        setAttr(BUS_ATTR_RXTX_PARITYBIT, paritybit);
    }

    public boolean getRxtxDTR() {
        return getOrDefault(BUS_ATTR_RXTX_DTR, false);
    }

    public void setRxtxDTR(boolean dtr) {
        setAttr(BUS_ATTR_RXTX_DTR, dtr);
    }

    public boolean getRxtxRTS() {
        return getOrDefault(BUS_ATTR_RXTX_RTS, false);
    }

    public void setRxtxRTS(boolean rts) {
        setAttr(BUS_ATTR_RXTX_RTS, rts);
    }
}
