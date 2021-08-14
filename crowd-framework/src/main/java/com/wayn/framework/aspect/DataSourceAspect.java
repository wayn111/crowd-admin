package com.wayn.framework.aspect;


import com.wayn.common.annotation.DataSource;
import com.wayn.common.enums.DataSourceEnum;
import com.wayn.framework.manager.thread.DataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Order(1) // 设置order，指定数据源切面在遇到事务切面之前执行
@Component
public class DataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.wayn.common.annotation.DataSource)"
            + "|| @within(com.wayn.common.annotation.DataSource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource annotation = signature.getMethod().getAnnotation(DataSource.class);
        if (Objects.isNull(annotation)) {
            DataSourceHolder.set(DataSourceEnum.MASTER);
        } else {
            DataSourceHolder.set(annotation.value());
        }
        try {
            return point.proceed();
        } finally {
            DataSourceHolder.remove();
        }
    }

}
