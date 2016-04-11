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

import com.cft.bean.ConfigurationMgmtBean;
import com.cft.bean.UserBean;
import com.cft.bean.VehicleBean;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.cft.pojo.BookTour;
import com.cft.pojo.Template;
import com.cft.pojo.User;
import com.cft.pojo.Vehicle;
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

public class ConfigurationMgmtServices {  

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/templates")  
	public String getTemplates( ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : getTemplates" );

		ConfigurationMgmtBean configurationMgmtBean = (ConfigurationMgmtBean)InitialLoader.ctx.getBean("configurationMgmtBean");
		 List<Template> templates= configurationMgmtBean.getTemplates();

		return Reply.formatReply(templates,ExceptionCode.SCS);

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/template")  
	public String addUpdateTemplate(Template template ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : addUpdateTemplate" +  Utils.objectToJsonStirng(template));

		ConfigurationMgmtBean configurationMgmtBean = (ConfigurationMgmtBean)InitialLoader.ctx.getBean("configurationMgmtBean");

		if(template.getTemplateId()==null){
		
		}else{
			configurationMgmtBean.updateTemplate(template);
		}

		return Reply.formatReply(template,ExceptionCode.SCS);
		
		

	}
	
	

}   