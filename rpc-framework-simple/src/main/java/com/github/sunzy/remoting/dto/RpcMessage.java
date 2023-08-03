package com.github.sunzy.remoting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunzy
 * @date 2023/8/3 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpcMessage {

    private byte messageType;

    private byte codec;

    private byte compress;

    private int requestId;

    private Object data;
}
