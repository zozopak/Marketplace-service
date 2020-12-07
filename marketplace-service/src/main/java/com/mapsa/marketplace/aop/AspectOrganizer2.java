package com.mapsa.marketplace.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectOrganizer2 {

    @AfterReturning(pointcut = "execution(* com.mapsa.marketplace.controller.*.update*(..))",returning = "result")
    public void getResult(Object result){

        log.info("Entity has changed sucsessfully!");
        System.out.println(result);

    }
}
