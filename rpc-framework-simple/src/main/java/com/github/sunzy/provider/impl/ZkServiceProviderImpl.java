package com.github.sunzy.provider.impl;

import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.register.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sunzy
 * @date 2023/8/5 16:20
 */
@Slf4j
public class ZkServiceProviderImpl implements ServiceProvider {


    /**
     * key: rpc service name (interface name + version + group)
     * value: service object
     */
    private final Map<String, Object> serviceMap;

    private final Set<String> registeredService;

    private final ServiceRegistry serviceRegistry;


    public ZkServiceProviderImpl() {
        serviceMap = new ConcurrentHashMap<>();
        registeredService = ConcurrentHashMap.newKeySet();
        this.serviceRegistry = serviceRegistry;
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
