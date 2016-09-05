package com.cft.ws;  
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cft.bean.UserBean;
import com.cft.bean.VehicleBean;
import com.cft.entity.User;
import com.cft.entity.UserVehicle;
import com.cft.entity.UserVehicleView;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.javatpoint.InitialLoader;
import com.ss.bean.CommunicateBean;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.GenericVariables.ROLE;
import com.ss.utility.Reply;
import com.ss.utility.Utils;

@Path("/")  

public class UserServices {  

	private static final Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/suscribe")  
	public String suscribe( @QueryParam(value = "email") String email ){ 
		
		logger.info("method : suscribe"  );
		
		CommunicateBean communicable = new CommunicateBean();
		
		communicable.suscribe(email);
				
		logger.info("input : "+ email);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user")  
	public String addUser( User user  ) throws UserAlreadyExist{ 

		logger.info("method : addUser" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		userBean.registerUser(user);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/verify")  
	public void verifyUserRegistration(@QueryParam(value = "tk") String token, @Context HttpServletResponse response,
            @Context HttpServletRequest request) throws ServletException, IOException{ 

		logger.info("method : VerifyUserRegistration" +"token : "+ token );
		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		int isVerified = userBean.verifyUserRegistration(token);
		
		if(isVerified==0){
			 response.sendRedirect("../../login.html");
		}else{
		
		 response.sendRedirect("../../login.html");
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/login")  
	public String loginUser(User user) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : loginUser" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		user= userBean.loginUser(user);
		
		
		return Reply.formatReply(user,ExceptionCode.SCS);

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/vendor/login")  
	public String loginVendorUser( User user) throws UnAuthorisedUser, UnActiveUser { 
		
		logger.info("method : loginVendorUser" +"input : "+ Utils.objectToJsonStirng(user) );
	
		
		//logger.info("method : loginVendorUser" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		
		user.setUserRoles(ROLE.Vendor);
		user= userBean.loginUser(user);

		return Reply.formatReply(user,ExceptionCode.SCS);

	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/forgotpass")  
	public String forgotpass(User user) throws UnAuthorisedUser { 

		logger.info("method : forgotpass" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		userBean.forgotpass(user);

		return Reply.formatReply("",ExceptionCode.SCS);

	} 



	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/users")  
	public String getAllUsers() throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : getAllUsers" );

		UserBean userBean=InitialLoader.ctx.getBean(UserBean.class);
		 List<User> user= userBean.getAllUsers();

		return Reply.formatReply(user,ExceptionCode.SCS);

	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/{userId}/vehicle")  
	public String addUpdateUserVehicle(UserVehicle userVehicle, @PathParam("userId") Integer userId  ) throws UnAuthorisedUser, UnActiveUser { 

		userVehicle.setUserId(userId);
		logger.info("method : addUpdateUserVehicle" +  Utils.objectToJsonStirng(userVehicle));

		VehicleBean vehicleBean =InitialLoader.ctx.getBean(VehicleBean.class);

		if(userVehicle.getUserVehicleId()==null){
			Integer userVehicleId= vehicleBean.addUserVehicle(userVehicle);
			userVehicle.setUserVehicleId(userVehicleId);
		}else{
			vehicleBean.updateUserVehicle(userVehicle);
		}

		return Reply.formatReply(userVehicle,ExceptionCode.SCS);
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user{p:/?}{userId:.*}/vehicles")  
	public String getUserVehicles( @PathParam("userId")  String userIdStr  ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("Method : getUserVehicles - userId : " +  userIdStr );
		Integer userId = null;
		if(!Utils.isEmpty(userIdStr)){
			userId = Integer.parseInt(userIdStr);
		}
		
		VehicleBean vehicleBean =InitialLoader.ctx.getBean(VehicleBean.class);
		List<UserVehicleView> userVehicles= vehicleBean.getUserVehicles(userId);
		return Reply.formatReply(userVehicles,ExceptionCode.SCS);
		
	}




}