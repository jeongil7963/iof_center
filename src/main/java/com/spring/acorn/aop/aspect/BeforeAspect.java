package com.spring.acorn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("before")
public class BeforeAspect {
	
	@Before("execution(public * com.spring.acorn..*Impl.e*(..))")
	public void before(JoinPoint point){
		String method = point.getSignature().getName();
		Object obj = point.getTarget();
		
		System.out.println("[BeforeAspect] >>>>>>>>>>>>>>>>>>>> " + method + "\t" + obj);
		
	}
}
