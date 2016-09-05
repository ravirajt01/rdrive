package com.cft.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Body;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cft.bean.CityBean;
import com.cft.bean.BookTourBean;
import com.cft.bean.UserBean;
import com.cft.bean.CityBean;
import com.cft.entity.BookTour;
import com.cft.entity.City;
import com.cft.entity.User;
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



public class AdminServices {

	private static final Logger logger = Logger.getLogger(AdminServices.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/admin/bookings")  
	public String getbookings(){ 
		
		logger.info("method : getbookings");
		BookTourBean bookTourBean = InitialLoader.ctx.getBean(BookTourBean.class);
		
		
		List<BookTour> bookTour = bookTourBean.getbookings();

		return Reply.formatReply(bookTour,ExceptionCode.SCS);

	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/admin/cities")  
	public String getcities(){ 
		
		logger.info("method : getcities");
		CityBean cityBean = InitialLoader.ctx.getBean(CityBean.class);
		
		
		List<City> city =cityBean.getcities();

		return Reply.formatReply(city,ExceptionCode.SCS);

	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/admin/city")  
	public String addUpdateCities(City city ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : addUpdateCitys" +  Utils.objectToJsonStirng(city));

		CityBean cityBean = InitialLoader.ctx.getBean(CityBean.class);

		if(city.getCityId()==null){
			Integer cityId= cityBean.addCity(city);
			city.setCityId(cityId);
		}else{
			cityBean.updateCity(city);
		}

		return Reply.formatReply(city,ExceptionCode.SCS);
		
		

	}
	
}

