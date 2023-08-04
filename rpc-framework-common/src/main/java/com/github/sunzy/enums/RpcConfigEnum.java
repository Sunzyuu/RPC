package com.github.sunzy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunzy
 * @date 2023/8/4 17:19
 */
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {

    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;
}
