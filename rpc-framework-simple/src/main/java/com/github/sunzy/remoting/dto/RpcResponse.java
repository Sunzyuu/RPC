package com.github.sunzy.remoting.dto;

import com.github.sunzy.enums.RpcResponseCodeEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author sunzy
 * @date 2023/8/3 15:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = 715745410605631233L;
    private String requestId;

    private Integer code;

    private String message;

    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcResponseCodeEnum.SUCCESS.getCode());
        response.setMessage(RpcResponseCodeEnum.SUCCESS.getMessage());
        response.setRequestId(requestId);
        if(data != null) {
            response.setData(data);
        }
        return response;
    }
    public static <T> RpcResponse<T> fail(RpcResponseCodeEnum rpcResponseCodeEnum) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(rpcResponseCodeEnum.getCode());
        response.setMessage(rpcResponseCodeEnum.getMessage());
        return response;
    }
}
