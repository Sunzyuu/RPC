package com.github.sunzy.register;

import com.github.sunzy.extension.SPI;

import java.net.InetSocketAddress;

/**
 * @author sunzy
 * @date 2023/8/5 16:25
 */
@SPI
public interface ServiceRegistry {

    /**
     * register service
     * @param rpcServiceName
     * @param inetSocketAddress
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
