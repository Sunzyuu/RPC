package com.github.sunzy.registry;

import com.github.sunzy.extension.SPI;
import com.github.sunzy.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @author sunzy
 * @date 2023/8/3 21:40
 */
@SPI
public interface ServiceDiscovery {
    InetSocketAddress lookupService(RpcRequest request);
}
