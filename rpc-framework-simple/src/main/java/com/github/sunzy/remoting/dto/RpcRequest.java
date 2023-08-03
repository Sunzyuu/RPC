package com.github.sunzy.remoting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sunzy
 * @date 2023/8/3 15:30
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041956251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }


}
