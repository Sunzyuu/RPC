package com.github.sunzy.remoting.transport;

import com.github.sunzy.remoting.dto.RpcRequest;

import java.io.IOException;

/**
 * @author sunzy
 * @date 2023/8/3 21:18
 */
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     *
     * @param request message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest request) throws IOException;
}
