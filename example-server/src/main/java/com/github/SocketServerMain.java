package com.github;

import com.github.serviceimpl.HelloServiceImpl;
import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.remoting.transport.socket.SocketRpcServer;

/**
 * @author sunzy
 * date 2023-08-20
 */
public class SocketServerMain {

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(helloService);
        socketRpcServer.registerService(rpcServiceConfig);
        socketRpcServer.start();
    }

}
