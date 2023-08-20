package com.github;

import com.github.serviceimpl.HelloServiceImpl;
import com.github.sunzy.HelloService;
import com.github.sunzy.annotation.RpcScan;
import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.remoting.transport.netty.server.NettyRpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sunzy
 * date 2023-08-20
 */
@RpcScan(basePackage = {"com.github.sunzy"})
public class NettyServerMain {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test1").version("version1").service(helloService2).build();
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }
}
