package com.github.sunzy.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * 这段代码定义了一个名为 CustomScanner 的类，它继承了 Spring 框架中的 ClassPathBeanDefinitionScanner 类。
 * 这个自定义扫描器的主要作用是用于扫描指定包中的类，并将符合特定注解类型（由 annoType 参数指定）的类注册为 Spring Bean 定义。
 * @author sunzy
 * date 2023-08-20
 */
public class CustomScanner extends ClassPathBeanDefinitionScanner {
    /**
     * @param registry BeanDefinitionRegistry类型的对象
     * @param annoType 注解类型的 Class 对象
     */
    public CustomScanner(BeanDefinitionRegistry registry,Class<? extends Annotation> annoType) {
        super(registry);
        // 设置包含过滤器，以便只扫描包含指定注解类型的类
        super.addIncludeFilter(new AnnotationTypeFilter(annoType));
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }
}
