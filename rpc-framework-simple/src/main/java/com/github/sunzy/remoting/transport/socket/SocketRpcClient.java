package com.github.sunzy.remoting.transport.socket;

import com.github.sunzy.enums.ServiceDiscoveryEnum;
import com.github.sunzy.extension.ExtensionLoader;
import com.github.sunzy.register.ServiceDiscovery;
import com.github.sunzy.remoting.dto.RpcRequest;
import com.github.sunzy.remoting.transport.RpcRequestTransport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 基于socket传输RpcRequest
 * @author sunzy
 * @date 2023/8/3 21:36
 */
@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {

    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        this.serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension(ServiceDiscoveryEnum.ZK.getName());
    }


    @Override
    public Object sendRpcRequest(RpcRequest request){
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(request);
        try(Socket socket = new Socket()) {
            //socket connect server
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
