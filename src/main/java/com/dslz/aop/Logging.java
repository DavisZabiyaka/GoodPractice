package com.dslz.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {
	
	/*@Pointcut("execution(* org.springframework.orm.hibernate5.LocalSessionFactoryBean.*(..))")
	public void theSession() {
		
	}
	
	@Before("theSession()")
	public void beforeAdvice() {
		System.out.println("Begin Session.");
	}*/
	
	@Pointcut("execution(* com.dslz.beans.Contact.setName(..))")
	public void settingTheNameOfContact() {
		
	}
	
	@Before("settingTheNameOfContact()")
	public void beforeAdvice() {
		System.out.println("Preparing to set contact.");
	}
}
