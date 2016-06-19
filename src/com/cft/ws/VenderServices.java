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
import com.cft.bean.VenderBean;
import com.cft.entity.BookTour;
import com.cft.entity.User;
import com.cft.entity.Vender;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.cft.exception.UserNotExist;
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





public class VenderServices {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vender")  
	public String addVender( Vender vender  ) throws UserAlreadyExist{ 

		System.out.println("method : addVender" +"input : "+ Utils.objectToJsonStirng(vender) );

		VenderBean venderBean=(VenderBean)InitialLoader.ctx.getBean("venderBean");
		venderBean.registerVender(vender);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vender/verify")  
	public void verifyVenderRegistration(@QueryParam(value = "tk") String token, @Context HttpServletResponse response,
            @Context HttpServletRequest request) throws ServletException, IOException{ 

		System.out.println("method : VerifyVenderRegistration" +"token : "+ token );
		VenderBean venderBean=(VenderBean)InitialLoader.ctx.getBean("venderBean");
		int isVerified = venderBean.verifyVenderRegistration(token);
		
		if(isVerified==0){
			 response.sendRedirect("../../venderLogin.html");
		}else{
		
		 response.sendRedirect("../../venderLogin.html");
		}
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vender/login")  
	public String loginVender( Vender vender  ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : loginUser" +"input : "+ Utils.objectToJsonStirng(vender) );

		VenderBean venderBean=(VenderBean)InitialLoader.ctx.getBean("venderBean");
		vender= venderBean.loginVender(vender);

		return Reply.formatReply(vender,ExceptionCode.SCS);

	}
	
}
