package com.ss.exceptions;

import java.util.logging.Logger;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.MethodNotAllowedException;
import org.jboss.resteasy.spi.NotFoundException;

import com.cft.exception.RecordAlreadyExist;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.cft.exception.UserNotExist;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;

@Provider
@Produces("application/json")
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger logger = Logger.getLogger(CustomExceptionMapper.class.getName());

	@Override
	public Response toResponse(Throwable throwable) {
		
		logger.info("Exception Cause:===>"+throwable.getCause());
		logger.info("Exception class:===>"+throwable.getClass());
		logger.info("Exception message:=>"+throwable.getMessage());
		
		ExceptionCode exceptionCode = ExceptionCode.ISE ;
		
		if(throwable.getClass().equals(NotFoundException.class) || throwable.getClass().equals(MethodNotAllowedException.class) ){
			
			exceptionCode = ExceptionCode.RNF ;
		}
		if(throwable.getClass().equals(MethodNotAllowedException.class) ){
			
			exceptionCode = ExceptionCode.MNA ;
		}if(throwable.getClass().equals(UserAlreadyExist.class) ){
			
			exceptionCode = ExceptionCode.UAE ;
		}if(throwable.getClass().equals(UnAuthorisedUser.class) ){
			
			exceptionCode = ExceptionCode.UNAU ;
		}if(throwable.getClass().equals(UnActiveUser.class)  ){
			
			exceptionCode = ExceptionCode.NAU ;
		}if(throwable.getClass().equals(UserNotExist.class)  ){
			
			exceptionCode = ExceptionCode.UNE ;
		}if(throwable.getClass().equals(RecordAlreadyExist.class)  ){
			
			exceptionCode = ExceptionCode.RAE ;
		}
		else{

			throwable.printStackTrace();
		}
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, ClientType")
				.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS")
				.header("Access-Control-Allow-Credentials", "true").entity(Reply.formatReply(null,exceptionCode)).build();
		
		
		
	}

	
	
}
