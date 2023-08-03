package com.github.sunzy.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunzy
 * @date 2023/8/3 21:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RpcServiceConfig {
    /**
     * service version
     */
    private String version = "";
    /**
     * when the interface has multiple implementation classes, distinguish by group
     */
    private String group = "";

    /**
     * target service
     */
    private Object service;

    public String getRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }

    public String getServiceName() {
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }
}