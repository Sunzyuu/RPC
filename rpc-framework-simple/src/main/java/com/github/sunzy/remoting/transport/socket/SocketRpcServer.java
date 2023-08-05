package com.github.sunzy.remoting.transport.socket;

import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.factory.SingletonFactory;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.provider.impl.ZkServiceProviderImpl;
import com.github.sunzy.utils.concurrent.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;

/**
 * @author sunzy
 * @date 2023/8/5 21:37
 */
@Slf4j
public class SocketRpcServer {
    public static final int PORT = 9998;
    private final ExecutorService threadPool;
    private final ServiceProvider serviceProvider;
    public SocketRpcServer() {
        threadPool = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
    }

    public void registerService(RpcServiceConfig rpcServiceConfig) {
        serviceProvider.publishService(rpcServiceConfig);
    }

    public void start() {
        try(ServerSocket server = new ServerSocket()) {
            String host = InetAddress.getLocalHost().getHostAddress();
            server.bind(new InetSocketAddress(host, PORT));
            Socket socket;

            while((socket = server.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
            threadPool.shutdown();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
