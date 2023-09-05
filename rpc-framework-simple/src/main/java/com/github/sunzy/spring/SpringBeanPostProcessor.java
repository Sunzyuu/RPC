package com.github.sunzy.spring;

import com.github.sunzy.annotation.RpcReference;
import com.github.sunzy.annotation.RpcService;
import com.github.sunzy.config.RpcServiceConfig;
import com.github.sunzy.enums.RpcRequestTransportEnum;
import com.github.sunzy.extension.ExtensionLoader;
import com.github.sunzy.factory.SingletonFactory;
import com.github.sunzy.provider.ServiceProvider;
import com.github.sunzy.provider.impl.ZkServiceProviderImpl;
import com.github.sunzy.proxy.RpcClientProxy;
import com.github.sunzy.remoting.transport.RpcRequestTransport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author sunzy
 * date 2023-08-20
 */
@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;
    private final RpcRequestTransport rpcClient;

    public SpringBeanPostProcessor() {
        this.serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
        this.rpcClient = ExtensionLoader.getExtensionLoader(RpcRequestTransport.class).getExtension(RpcRequestTransportEnum.NETTY.getName());
    }

    /**
     *  在每个 Bean 初始化之前被调用。该方法检查当前 Bean 是否被标记为 @RpcService 注解，
     *  如果是，就将该服务发布到服务提供者。也就是说，
     *  当一个带有 @RpcService 注解的 Bean 在 Spring 容器中被初始化之前，
     *  该方法会将它注册为一个可供远程调用的服务。
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());
            // get RpcService annotation
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
            // build RpcServiceProperties
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();
            serviceProvider.publishService(rpcServiceConfig);
        }
        return bean;
    }

    /**
     * 在每个 Bean 初始化之后被调用。该方法检查当前 Bean 是否有属性被标记为 @RpcReference 注解，如果有，
     * 就创建一个 RPC 客户端代理，并将代理赋值给属性。
     * 换句话说，当一个 Bean 初始化完成后，该方法会检查它是否有需要远程引用的属性，并创建代理对象，
     * 以便在需要时进行远程调用
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                        .group(rpcReference.group())
                        .version(rpcReference.version()).build();
                RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient, rpcServiceConfig);
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }
}
