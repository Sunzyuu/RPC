package com.github.sunzy.remoting.transport.socket;

import com.github.sunzy.factory.SingletonFactory;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.provider.impl.ZkServiceProviderImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * @author sunzy
 * @date 2023/8/5 21:37
 */
@Slf4j
public class SocketRpcServer {
//    private final ExecutorService threadPool;
    private final ServiceProvider serviceProvider;
    public SocketRpcServer() {
//        threadPool = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
    }

}
