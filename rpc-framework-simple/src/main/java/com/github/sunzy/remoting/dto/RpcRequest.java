package com.github.sunzy.remoting.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author sunzy
 * @date 2023/8/3 15:30
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    // 请求的接口名
    private String interfaceName;
    // 请求的方法名
    private String methodName;
    // 方法所需参数
    private Object[] parameters;
    // 方法所需参数的类型
    private Class<?>[] paramTypes;
    // 方法版本号
    private String version;
    // 服务组
    private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
