package com.github.sunzy.annotation;

import com.github.sunzy.spring.CustomScannerRegistry;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author sunzy
 * date 2023-08-20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomScannerRegistry.class)
@Documented
public @interface RpcScan {
    String[] basePackage();
}
