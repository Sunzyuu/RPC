package com.github.sunzy.registry.zk;

import com.github.sunzy.enums.LoadBalanceEnum;
import com.github.sunzy.enums.RpcErrorMessageEnum;
import com.github.sunzy.exception.RpcException;
import com.github.sunzy.extension.ExtensionLoader;
import com.github.sunzy.loadbalance.LoadBalance;
import com.github.sunzy.loadbalance.loadbalancer.RandomLoadBalance;
import com.github.sunzy.registry.ServiceDiscovery;
import com.github.sunzy.registry.zk.util.CuratorUtils;
import com.github.sunzy.remoting.dto.RpcRequest;
import com.github.sunzy.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.util.List;

import static com.github.sunzy.extension.ExtensionLoader.*;

/**
 * @author sunzy
 * @date 2023/8/3 22:42
 */
@Slf4j
public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
    private final LoadBalance loadBalance;

    public ZkServiceDiscoveryImpl() {
        this.loadBalance = getExtensionLoader(LoadBalance.class).getExtension(LoadBalanceEnum.LOADBALANCE.getName());
    }
    /**
     * 将客户端请求的服务从zookeeper中取出，进行调用
     * @param rpcRequest
     * @return
     */
    @Override
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        String rpcServiceName = rpcRequest.getRpcServiceName();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceUrlList = CuratorUtils.getChildrenNodes(zkClient, rpcServiceName);
        if (CollectionUtil.isEmpty(serviceUrlList)) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        // load balancing
        String targetServiceUrl = loadBalance.selectServiceAddress(serviceUrlList, rpcRequest);
        log.info("Successfully found the service address:[{}]", targetServiceUrl);
        String[] socketAddressArray = targetServiceUrl.split(":");
        String host = socketAddressArray[0];
//        host = "127.0.0.1";
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);
    }
}
