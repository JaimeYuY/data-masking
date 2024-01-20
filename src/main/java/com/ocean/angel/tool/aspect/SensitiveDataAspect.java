package com.ocean.angel.tool.aspect;

import com.ocean.angel.tool.annotation.SensitiveMethod;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class SensitiveDataAspect {

    @Pointcut("@annotation(com.ocean.angel.tool.annotation.SensitiveMethod)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object sensitiveAround(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = pjp.proceed();
        returnValue = sensitive(pjp, returnValue);
        return returnValue;
    }

    public Object sensitive(JoinPoint pointer, Object returnValue) {

        Signature signature = pointer.getSignature();

        try {
            MethodSignature sign = (MethodSignature) signature;
            Method method = sign.getMethod();
            //获取方法上的注解
            SensitiveMethod sensitive = method.getAnnotation(SensitiveMethod.class);
            if (null != sensitive) {
                SensitiveDataHandler.handle(returnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
