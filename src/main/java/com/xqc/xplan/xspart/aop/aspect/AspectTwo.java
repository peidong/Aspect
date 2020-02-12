package com.xqc.xplan.xspart.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class AspectTwo {

    @After("@annotation(com.xqc.xplan.xspart.aop.annotation.BuryingPoint)")
    public void after(JoinPoint joinPoint)
    {
        System.out.println("=======AspectTwo after start=======");
        System.out.println(joinPoint.getArgs()[0]);
        //System.out.println(1/0);
        System.out.println("=======AspectTwo after end=======");
    }
}
