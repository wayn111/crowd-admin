package com.wayn.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;


/**
 * 继承SpringBeanJobFactory接口获取Job实例，
 * 并通过AutowireCapableBeanFactory
 * 将Job实例交给Spring管理生命周期
 */
@Component
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {

    /**
     * 原对象可以不在Spring的IOC容器里，但是其属性成员需要被依赖注入，可以通过AutowireCapableBeanFactory
     * 来实现属性的注入
     */
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}
