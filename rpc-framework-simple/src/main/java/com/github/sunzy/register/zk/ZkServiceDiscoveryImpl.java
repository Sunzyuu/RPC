package com.github.sunzy.register.zk;

import com.github.sunzy.loadbalance.LoadBalance;
import com.github.sunzy.loadbalance.loadbalancer.RandomLoadBalance;
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
    private final LoadBalance loadBalance;

    public ZkServiceDiscoveryImpl() {
        this.loadBalance = new RandomLoadBalance();
    }

    @Override
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        String rpcServiceName = rpcRequest.getRpcServiceName();

        return null;
    }
}
