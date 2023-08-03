package com.github.test.remoting.netty.dto;

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
public class RpcResponse {
    private String message;
}
