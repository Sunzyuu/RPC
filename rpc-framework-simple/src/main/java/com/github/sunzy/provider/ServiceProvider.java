package com.github.sunzy.provider;

import com.github.sunzy.config.RpcServiceConfig;

/**
 * @author sunzy
 * @date 2023/8/3 21:06
 */
public interface ServiceProvider {

    void addService();

    Object getService(String rpcServiceName);


    void publicService(RpcServiceConfig rpcServiceConfig);
}
