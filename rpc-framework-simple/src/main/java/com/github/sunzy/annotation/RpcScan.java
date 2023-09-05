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
//  Spring 框架中引入其他配置类或组件
@Import(CustomScannerRegistry.class)
@Documented
public @interface RpcScan {
    String[] basePackage();
}
