package com.github.sunzy.loadbalance;

import com.github.sunzy.extension.SPI;
import com.github.sunzy.remoting.dto.RpcRequest;

import java.util.List;

/**
 * @author sunzy
 * @date 2023/8/3 22:49
 */
@SPI
public interface LoadBalance {

    String selectServiceAddress(List<String> serviceUrlList, RpcRequest request);
}
