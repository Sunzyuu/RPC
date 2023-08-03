package com.github.sunzy.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author sunzy
 * @date 2023/8/3 16:00
 */

@AllArgsConstructor
@Getter
public enum RpcResponseCodeEnum {
    SUCCESS(200, "The remote call is successful"),
    FAIL(500, "The remote call is fail");
    private final int code;
    private final String message;
}
