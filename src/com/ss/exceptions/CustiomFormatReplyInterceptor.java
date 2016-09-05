package com.ss.exceptions;


import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;

@Provider
@ServerInterceptor
public class CustiomFormatReplyInterceptor implements PostProcessInterceptor{

	@Context
	  private HttpServletResponse serverResponse;
	
	@Override
	public void postProcess(ServerResponse response) {
		serverResponse.setHeader("Access-Control-Allow-Origin", "*");
		serverResponse.setHeader("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, ClientType");
		serverResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		serverResponse.setHeader("Access-Control-Allow-Credentials", "true");
		
	}

}
