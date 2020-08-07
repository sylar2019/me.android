package me.java.library.io.store.coap;

import me.java.library.io.store.coap.client.CoapClientPipe;
import me.java.library.io.store.coap.server.CoapServerPipe;

/**
 * File Name             :  CoapExpress
 *
 * @author :  sylar
 * Create                :  2020/7/22
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) allthings.vip  All Rights Reserved
 * *******************************************************************************************
 */
public class CoapExpress {
    public static CoapServerPipe server() {
        return new CoapServerPipe();
    }
    public static CoapClientPipe client() {
        return new CoapClientPipe();
    }
}