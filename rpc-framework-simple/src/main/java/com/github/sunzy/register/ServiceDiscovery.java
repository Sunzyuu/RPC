package com.github.sunzy.register;

import com.github.sunzy.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * @author sunzy
 * @date 2023/8/3 21:40
 */
public interface ServiceDiscovery {
    InetSocketAddress lookupService(RpcRequest request);
}
