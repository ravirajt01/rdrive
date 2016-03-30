package com.cft.ws;  
import java.io.IOException;
import java.util.List;

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
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Body;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cft.bean.UserBean;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.cft.exception.UserNotExist;
import com.cft.pojo.BookTour;
import com.cft.pojo.User;
import com.javatpoint.Employee;
import com.javatpoint.EmployeeDao;
import com.javatpoint.InitialLoader;
import com.javatpoint_old.AccountsDao;
import com.ss.bean.Autheticable;
import com.ss.bean.AutheticatorBean;
import com.ss.bean.Communicable;
import com.ss.bean.CommunicateBean;
import com.ss.pojo.LoginUser;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;
import com.ss.utility.Utils;
import com.studytrails.tutorials.springhibernatejpa.Person;
import com.studytrails.tutorials.springhibernatejpa.PersonDao;
@Path("/")  

public class UserServices {  
/*
	@Autowired
	private PersonDao personDao;*/
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/suscribe")  
	public String suscribe( @QueryParam(value = "email") String email ){ 

		System.out.println("method : suscribe"  );
		
		CommunicateBean communicable = new CommunicateBean();
		
		communicable.suscribe(email);
				
		System.out.println("input : "+ email);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user")  
	public String addUser( User user  ) throws UserAlreadyExist{ 

		System.out.println("method : addUser" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=(UserBean)InitialLoader.ctx.getBean("userBean");
		userBean.registerUser(user);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/verify")  
	public void verifyUserRegistration(@QueryParam(value = "tk") String token, @Context HttpServletResponse response,
            @Context HttpServletRequest request) throws ServletException, IOException{ 

		System.out.println("method : VerifyUserRegistration" +"token : "+ token );
		UserBean userBean=(UserBean)InitialLoader.ctx.getBean("userBean");
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
	public String loginUser( User user  ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : loginUser" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=(UserBean)InitialLoader.ctx.getBean("userBean");
		user= userBean.loginUser(user);

		return Reply.formatReply(user,ExceptionCode.SCS);

	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/forgotpass")  
	public String forgotpass( User user  ) throws UnAuthorisedUser { 

		System.out.println("method : forgotpass" +"input : "+ Utils.objectToJsonStirng(user) );

		UserBean userBean=(UserBean)InitialLoader.ctx.getBean("userBean");
		userBean.forgotpass(user);

		return Reply.formatReply("",ExceptionCode.SCS);

	} 



	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/users")  
	public String getAllUsers(  ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : getAllUsers" );

		UserBean userBean=(UserBean)InitialLoader.ctx.getBean("userBean");
		 List<User> user= userBean.getAllUsers();

		return Reply.formatReply(user,ExceptionCode.SCS);

	}


}