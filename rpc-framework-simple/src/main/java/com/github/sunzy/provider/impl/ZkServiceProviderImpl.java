package com.github.sunzy.provider.impl;

import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.provider.ServiceProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * @author sunzy
 * @date 2023/8/5 16:20
 */
@Slf4j
public class ZkServiceProviderImpl implements ServiceProvider {


    private final Map<String, Object> serviceMap;

    private final Set<String> registeredService;

    public ZkServiceProviderImpl(Map<String, Object> serviceMap, Set<String> registeredService) {
        this.serviceMap = serviceMap;
        this.registeredService = registeredService;
    }

    //    private final ServiceRegistry
    @Override
    public void addService(RpcServiceConfig rpcServiceConfig) {

    }

    @Override
    public Object getService(String rpcServiceName) {
        return null;
    }

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {

    }
}
