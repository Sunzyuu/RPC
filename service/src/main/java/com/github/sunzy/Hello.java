package com.github.sunzy;

import lombok.*;

import java.io.Serializable;

/**
 * @author sunzy
 * date 2023-08-20
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    private String message;
    private String description;
}
