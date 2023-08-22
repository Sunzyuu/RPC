package com.github.sunzy.loadbalance.loadbalancer;

import com.github.sunzy.loadbalance.AbstractLoadBalance;
import com.github.sunzy.remoting.dto.RpcRequest;

import java.util.List;
import java.util.Random;

/**
 * random load balancing strategy
 * @author sunzy
 * @date 2023/8/3 23:00
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    /**
     * 从服务列表中随机选择一个服务
     * @param serviceAddresses
     * @param rpcRequest
     * @return
     */
    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));

    }
}
