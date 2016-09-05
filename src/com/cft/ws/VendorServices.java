package com.cft.ws;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cft.bean.VendorBean;
import com.cft.entity.Vender;
import com.cft.exception.UserAlreadyExist;
import com.javatpoint.InitialLoader;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;
import com.ss.utility.Utils;
@Path("/")  


public class VendorServices {

	private static final Logger logger = Logger.getLogger(VehicleServices.class.getName());

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vender")  
	public String addVender( Vender vender  ) throws UserAlreadyExist{ 

		logger.info("method : addVender" +" input : "+ Utils.objectToJsonStirng(vender) );

		VendorBean venderBean=InitialLoader.ctx.getBean(VendorBean.class);
		venderBean.registerVender(vender);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vender/verify")  
	public void verifyVenderRegistration(@QueryParam(value = "tk") String token, @Context HttpServletResponse response,
            @Context HttpServletRequest request) throws ServletException, IOException{ 

		logger.info("method : VerifyVenderRegistration" +" token : "+ token );
		VendorBean venderBean=InitialLoader.ctx.getBean(VendorBean.class);
		int isVerified = venderBean.verifyVenderRegistration(token);
		
		if(isVerified==0){
			 response.sendRedirect("../../vendorLogin.html");
		}else{
		
		 response.sendRedirect("../../venderLogin.html");
		}
	}
	
	
	
}
