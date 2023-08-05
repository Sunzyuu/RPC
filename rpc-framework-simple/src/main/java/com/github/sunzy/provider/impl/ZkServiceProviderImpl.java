package com.github.sunzy.provider.impl;

import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.enums.ServiceRegistryEnum;
import com.github.sunzy.extension.ExtensionLoader;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.register.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        serviceRegistry = ExtensionLoader.getExtensionLoader(ServiceRegistry.class).getExtension(ServiceRegistryEnum.ZK.getName());
    }

    @Override
    public void addService(RpcServiceConfig rpcServiceConfig) {
        String rpcServiceName = rpcServiceConfig.getRpcServiceName();
        if(registeredService.contains(rpcServiceName)) {
            return;
        }

        registeredService.add(rpcServiceName);
        serviceMap.put(rpcServiceName, rpcServiceConfig.getService());
        log.info("Add service: {} and interface:{}", rpcServiceName, rpcServiceConfig.getService().getClass().getInterfaces());
    }

    @Override
    public Object getService(String rpcServiceName) {
        Object service = serviceMap.get(rpcServiceName);
        if (service == null) {
            throw new IllegalArgumentException();
        }
        return service;
    }

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            this.addService(rpcServiceConfig);
        } catch (UnknownHostException e) {
            log.error("occur exception when get host address", e);
        }
    }
}
