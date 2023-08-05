package com.github.sunzy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunzy
 * @date 2023/8/5 20:45
 */
@AllArgsConstructor
@Getter
public enum ServiceDiscoveryEnum {
    ZK("zk");
    private final String name;
}
