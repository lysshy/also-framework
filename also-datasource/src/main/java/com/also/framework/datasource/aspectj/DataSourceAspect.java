package com.also.framework.datasource.aspectj;


import com.also.framework.datasource.annotation.DataSource;
import com.also.framework.datasource.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.also.framework.datasource.annotation.DataSource)|| @within(com.also.framework.datasource.annotation.DataSource)")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = this.getDataSource(point);
        if (!Objects.isNull(dataSource)) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    private DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (!StringUtils.isEmpty(targetDataSource)) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            return method.getAnnotation(DataSource.class);
        }
    }
}