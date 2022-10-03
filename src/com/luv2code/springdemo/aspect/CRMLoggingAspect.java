package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declaration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerClass() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServiceClass() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoClass() {}
	
	@Pointcut("forControllerClass() || forServiceClass() || forDaoClass()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void beforeAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=======>> Before: ===>>" + method);
		
		// get the arguments
		
		Object [] args = theJoinPoint.getArgs();
		
		for (Object arg : args) {
			myLogger.info("===>> Arg: " + arg);
		}
		
	}
	
	// add @AfterReturnong advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	private void afterReturning(
			JoinPoint theJoinPoint, 
			String theResult
			) {

		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=======>> after retrning: ===>> " + method);
		
		myLogger.info("=>> Result ====>>>" + theResult);

	}

}
