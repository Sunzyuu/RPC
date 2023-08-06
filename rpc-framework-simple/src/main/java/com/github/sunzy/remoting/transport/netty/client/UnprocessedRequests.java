package com.github.sunzy.remoting.transport.netty.client;

import com.github.sunzy.remoting.dto.RpcResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于处理未被处理的请求
 * @author sunzy
 * @date 2023/8/6 18:30
 */
public class UnprocessedRequests {
    private static final Map<String, CompletableFuture<RpcResponse<Object>>> UNPROCESSED_RESPONSE_FUTURES = new ConcurrentHashMap<>();


    public void put(String requestId, CompletableFuture<RpcResponse<Object>> future) {
        UNPROCESSED_RESPONSE_FUTURES.put(requestId, future);
    }

    public void complete(RpcResponse<Object> response) {
        CompletableFuture<RpcResponse<Object>> completableFuture = UNPROCESSED_RESPONSE_FUTURES.remove(response.getRequestId());
        if(completableFuture != null) {
            completableFuture.complete(response);
        } else {
            throw new IllegalArgumentException();
        }

    }

}
