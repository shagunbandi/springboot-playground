package com.shagunbandi.playground.annotationGround;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ExampleAspect {

	@Autowired
	private AnnotationService service;

	@Around("@annotation(let)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime let) throws Throwable {
		long start = System.currentTimeMillis();

		// Read Headers
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String header = request.getHeader("Content-Type");
		System.out.println(header);

		// Printing Annotation Params
		System.out.println(Arrays.toString(let.roles()));

		// Run the method, add another argument
		Object[] args = joinPoint.getArgs();
		System.out.println(args.length);
		args[args.length - 1] = "New String";

		// Execute Program
		Object proceed = joinPoint.proceed(args);

		// Calling an external service
		System.out.println(service.printMe("Print Me"));

		// do something with result
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}

}