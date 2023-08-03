package com.github.sunzy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunzy
 * @date 2023/8/3 22:45
 */
@AllArgsConstructor
@Getter
public enum LoadBalanceEnum {
    LOADBALANCE("loadBalance");

    private final String name;
}
