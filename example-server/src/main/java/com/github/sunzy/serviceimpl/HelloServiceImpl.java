package com.github.sunzy.serviceimpl;

import com.github.sunzy.Hello;
import com.github.sunzy.HelloService;
import com.github.sunzy.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sunzy
 * date 2023-08-20
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class HelloServiceImpl implements HelloService {
    static {
        log.info("HelloServiceImpl init");
    }
    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl收到: {}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}
