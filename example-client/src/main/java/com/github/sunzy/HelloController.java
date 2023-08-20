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
        //如需使用 assert 断言，需要在 VM options 添加参数：-ea
        assert "Hello description is 222".equals(hello);
        Thread.sleep(12000);
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("111", "222")));
        }
    }
}
