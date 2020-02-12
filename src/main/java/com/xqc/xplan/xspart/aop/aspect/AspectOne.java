package com.xqc.xplan.xspart.aop.aspect;

import com.xqc.xplan.xspart.aop.bean.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
public class AspectOne
{

//    @Before("buryingPoint()")
//    public void before(JoinPoint joinPoint)
//    {
//        System.out.println("=======before start=======");
//        System.out.println(joinPoint.getArgs()[0]);
//        System.out.println("=======before end=======");
//    }
    @After("@annotation(com.xqc.xplan.xspart.aop.annotation.BuryingPoint)")
    public void after(JoinPoint joinPoint)
    {
        System.out.println("=======AspectOne after start=======");
        Data data = (Data) joinPoint.getArgs()[0];
        System.out.println(data);
        data.setExtraId(123456L);
        System.out.println("=======AspectOne after end=======");
    }

//    @AfterReturning("buryingPoint()")
//    public void afterReturning(JoinPoint joinPoint)
//    {
//        System.out.println("=======afterReturning start=======");
//        System.out.println(joinPoint.getArgs()[0]);
//        System.out.println("=======afterReturning end=======");
//    }
//
//    @AfterThrowing("buryingPoint()")
//    public void afterThrowing(JoinPoint joinPoint)
//    {
//        System.out.println("=======afterThrowing start=======");
//        System.out.println(joinPoint.getArgs()[0]);
//        System.out.println("=======afterThrowing end=======");
//    }
//
//    @Around("buryingPoint()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("=======around start=======");
//        System.out.println(proceedingJoinPoint.getArgs()[0]);
//        proceedingJoinPoint.proceed();
//        System.out.println("=======around end=======");
//
//    }
}
