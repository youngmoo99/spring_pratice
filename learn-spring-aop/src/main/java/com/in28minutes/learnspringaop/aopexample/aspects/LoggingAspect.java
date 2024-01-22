package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//Configuration
//AOP

@Configuration
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	//Pointcut - When?
	//execution(* com.in28minutes.learnspringaop.aopexample.business.*.*(..)
	//Before : 메서드 호출전에 실행
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()")
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
		logger.info("Before Aspect - {} is called with arguments: {}",joinPoint,joinPoint.getArgs());
		
		//Logic - What?
	}
	
	//After : 메서드가 성공적으로 실행되는지 익셉션이 발생하는지와 관계없이 메서드 실행후에 실행 
	@After("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()")
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		logger.info("After Aspect - {} has executed",joinPoint);
		
		//Logic - What?
	}
	// AfterThrowing : 메서드에 익셉션이 발생한 경우에만 실행
	@AfterThrowing(pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
					throwing = "exception")
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		logger.info("After Aspect - {} has thrown an exception {}",joinPoint, exception);
		
		//Logic - What?
	}
	//AfterReturning : 메서드가 성공적으로 실행된 경우에만 실행 
	@AfterReturning(pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
		returning = "resultValue")
	public void logMethodCallAfterSuccessfulException(JoinPoint joinPoint, Object resultValue) {
		logger.info("AfterReturning Aspect - {} has returned {}",joinPoint, resultValue);
		
		//Logic - What?
	}
}
