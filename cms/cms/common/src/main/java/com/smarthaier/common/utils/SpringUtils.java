package com.smarthaier.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringUtils implements BeanFactoryPostProcessor {
    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory beanFactory;

    public static <T> T getBean(Class<T> clazz) {
        return  beanFactory.getBean(clazz);
    }
    public static <T> T getBean(String beanName) {
        return  (T) beanFactory.getBean(beanName);
    }
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory = configurableListableBeanFactory;
    }


    
}
