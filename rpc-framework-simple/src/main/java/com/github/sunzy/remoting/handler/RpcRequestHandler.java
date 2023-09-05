package com.github.sunzy.remoting.handler;

import com.github.sunzy.factory.SingletonFactory;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.provider.impl.ZkServiceProviderImpl;
import com.github.sunzy.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sunzy
 * @date 2023/8/3 16:13
 */
@Slf4j
public class RpcRequestHandler {

    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
    }

    public Object handle(RpcRequest request) {
        // 服务发现,从注册中心根据接口名,版本号获取到相应的服务对象
        Object service = serviceProvider.getService(request.getRpcServiceName());
        // 通过反射调用目标对象的方法
        return invokeTargetMethod(request, service);
    }


    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result = null;

        try {
            // 反射调用
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 返回执行结果
        return result;

    }
}
