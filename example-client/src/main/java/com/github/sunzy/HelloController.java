package com.github.sunzy;

import com.github.sunzy.Hello;
import com.github.sunzy.HelloService;
import com.github.sunzy.annotation.RpcReference;
import org.springframework.stereotype.Component;

/**
 * @author sunzy
 * date 2023-08-20
 */
@Component
public class HelloController {
    @RpcReference(version = "version1", group = "test1")
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
        Thread.sleep(2000);
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("111", "222")));
        }
    }
}
