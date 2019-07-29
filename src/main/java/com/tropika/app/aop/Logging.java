package com.tropika.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect @Component
public class Logging {

	@Before("@annotation(com.tropika.app.aop.annotations.LogExecution)")
	public void logBefore(JoinPoint joinPoint) {
		log.info("calling "+joinPoint.getSignature().getName());
    }
	
	@After("@annotation(com.tropika.app.aop.annotations.LogExecution)")
	public void logAfter(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName()+" ended");
    }
}
