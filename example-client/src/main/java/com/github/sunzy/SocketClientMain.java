package com.github.sunzy;

import com.github.sunzy.Hello;
import com.github.sunzy.HelloService;
import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.proxy.RpcClientProxy;
import com.github.sunzy.remoting.transport.RpcRequestTransport;
import com.github.sunzy.remoting.transport.socket.SocketRpcClient;

/**
 * @author sunzy
 * date 2023-08-20
 */
public class SocketClientMain {
    public static void main(String[] args) {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String hello = helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
    }
}
