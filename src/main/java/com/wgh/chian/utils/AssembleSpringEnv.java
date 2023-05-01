package com.wgh.chian.utils;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @Author wughua
 * @Description AssembleSpringEnv
 * @Date 2023/4/15
 */
@Component
public class AssembleSpringEnv implements ApplicationContextAware {

    private static ApplicationContext context;

    public AssembleSpringEnv() {
    }

    public static <T> T getBean(Class<T> beanClazz) {
        return context.getBean(beanClazz);
    }

    public static <T> Collection<T> getBeans(Class<T> clazz) {
        Map<String, T> beanMap = context.getBeansOfType(clazz);
        return !beanMap.isEmpty() ? beanMap.values() : null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
