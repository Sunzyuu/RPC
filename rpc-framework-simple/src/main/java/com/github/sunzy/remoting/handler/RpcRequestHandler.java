package com.github.sunzy.remoting.handler;

import com.github.sunzy.provider.ServiceProvider;
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

    public RpcRequestHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Object handle(RpcRequest request) {
        Object service = serviceProvider.getService(request.getRpcServiceName());
        return invokeTargetMethod(request, service);
    }


    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result = null;

        try {
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

        return result;

    }
}
