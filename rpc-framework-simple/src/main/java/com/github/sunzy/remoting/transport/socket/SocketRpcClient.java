package com.github.sunzy.remoting.transport.socket;

import com.github.sunzy.register.ServiceDiscovery;
import com.github.sunzy.remoting.dto.RpcRequest;
import com.github.sunzy.remoting.transport.RpcRequestTransport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * @author sunzy
 * @date 2023/8/3 21:36
 */
@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {

//    private final ServiceDiscovery serviceDiscovery;




    @Override
    public Object sendRpcRequest(RpcRequest request){

        try(Socket socket = new Socket()) {

        } catch (Exception e) {

        }
        return null;
    }
}
