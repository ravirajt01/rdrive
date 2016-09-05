package com.mkyong.rest;  
  
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;  

import com.cft.ws.UserServices;
  
@Aspect  
public class TrackOperation{  

	
	private static final Logger logger = Logger.getLogger(TrackOperation.class.getName());
	
	@Pointcut("execution(* com.javatpoint_old.Operation.*(..))")  // working
	public void pointCutNameMethod(){}//pointcut name  
	
	@Before("pointCutNameMethod()")
	public void myadvice(JoinPoint jp){
		printMethodsAndParam(jp);
	}  

	@Pointcut("execution(* com.cft.bean.VehicleBean.*(..))")  
	public void pointCutForVehicleBean(){}

	@Before("pointCutForVehicleBean()")
	public void myadvice2(JoinPoint jp){
		printMethodsAndParam(jp);
	} 
	
	void printMethodsAndParam(JoinPoint jp){  
		StringBuilder sb = new StringBuilder("Method :");
		sb.append(jp.getSignature());
		sb.append("->param: ");

		Object[] signatureArgs = jp.getArgs();
		for (Object signatureArg: signatureArgs) {
			sb.append(signatureArg).append(",");
		}
		logger.info(sb.toString());
	}

}  