package com.github.sunzy.loadbalance;

import com.github.sunzy.remoting.dto.RpcRequest;

import java.util.List;

/**
 * @author sunzy
 * @date 2023/8/3 22:57
 */
public abstract class AbstractLoadBalance implements LoadBalance{

    @Override
    public String selectServiceAddress(List<String> serviceUrlList, RpcRequest request) {
        if(serviceUrlList.isEmpty()){
            return null;
        }
        if(serviceUrlList.size() == 1) {
            return serviceUrlList.get(0);
        }
        return doSelect(serviceUrlList, request);
    }

    protected abstract String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest);
}
