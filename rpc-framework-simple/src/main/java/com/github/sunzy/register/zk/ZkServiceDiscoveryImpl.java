package com.github.sunzy.register.zk;

import com.github.sunzy.register.ServiceDiscovery;
import com.github.sunzy.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author sunzy
 * @date 2023/8/3 22:42
 */
@Slf4j
public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
//    private final LoadBalance loadBalance;

    @Override
    public InetSocketAddress lookupService(RpcRequest request) {
        return null;
    }
}
