package com.ss.exceptions;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.MethodNotAllowedException;
import org.jboss.resteasy.spi.NotFoundException;

import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;

@Provider
@Produces("application/json")
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable throwable) {
		
		System.out.println("Exception Cause:===>"+throwable.getCause());
		System.out.println("Exception class:===>"+throwable.getClass());
		System.out.println("Exception message:=>"+throwable.getMessage());
		
		ExceptionCode exceptionCode = ExceptionCode.ISE ;
		
		if(throwable.getClass().equals(NotFoundException.class) || throwable.getClass().equals(MethodNotAllowedException.class) ){
			
			exceptionCode = ExceptionCode.RNF ;
		}
		if(throwable.getClass().equals(MethodNotAllowedException.class) ){
			
			exceptionCode = ExceptionCode.MNA ;
		}if(throwable.getClass().equals(UserAlreadyExist.class) || throwable.getClass().equals(UserAlreadyExist.class) ){
			
			exceptionCode = ExceptionCode.UAE ;
		}if(throwable.getClass().equals(UnAuthorisedUser.class) || throwable.getClass().equals(UnAuthorisedUser.class) ){
			
			exceptionCode = ExceptionCode.UNAU ;
		}if(throwable.getClass().equals(UnActiveUser.class) || throwable.getClass().equals(UnActiveUser.class) ){
			
			exceptionCode = ExceptionCode.NAU ;
		}else{

			throwable.printStackTrace();
		}
		
		
		
		
		
		
		
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, ClientType")
				.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS")
				.header("Access-Control-Allow-Credentials", "true").entity(Reply.formatReply(null,exceptionCode)).build();
		
		
		
	}

	
	
}
