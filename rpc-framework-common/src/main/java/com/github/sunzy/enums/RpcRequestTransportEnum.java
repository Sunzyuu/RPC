package com.github.sunzy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunzy
 * @date 2023/8/3 16:01
 */
@AllArgsConstructor
@Getter
public enum RpcRequestTransportEnum {

    NETTY("netty"),
    SOCKET("socket");

    private final String name;
}
