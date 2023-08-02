package com.github.rpc.remoting.netty.dto;

import lombok.*;

/**
 * @author sunzy
 * @date 2023/8/2 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
