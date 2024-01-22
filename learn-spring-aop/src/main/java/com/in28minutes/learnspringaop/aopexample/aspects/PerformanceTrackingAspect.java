package com.in28minutes.learnspringaop.aopexample.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
	@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.trackTimeAnnotation()")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//타이머 시작 
		long startTimeMillis = System.currentTimeMillis();
		//메소드실행 
		Object returnValue = proceedingJoinPoint.proceed();
		//타이머 멈춤
		long stopTimeMillis = System.currentTimeMillis();
		
		long exectuinDuration = stopTimeMillis - startTimeMillis;
		
		logger.info("Around Aspect - {} Method executed in {} ms", proceedingJoinPoint ,exectuinDuration);
		
		return returnValue;
	}
}
