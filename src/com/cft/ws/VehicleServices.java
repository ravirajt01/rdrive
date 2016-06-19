package com.cft.ws;  
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Body;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cft.bean.UserBean;
import com.cft.bean.VehicleBean;
import com.cft.entity.BookTour;
import com.cft.entity.Colour;
import com.cft.entity.Make;
import com.cft.entity.User;
import com.cft.entity.UserVehicle;
import com.cft.entity.Vehicle;
import com.cft.entity.VehiclePhoto;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
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

public class VehicleServices {  

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicles")  
	public String getVehicles( ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : getVehicles" );

		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");
		 List<Vehicle> vehicles= vehicleBean.getVehicles();

		return Reply.formatReply(vehicles,ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicles/make")  
	public String getVehiclesMakers( ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : getVehiclesMakers" );

		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");
		 List<Make> vehicles= vehicleBean.getVehiclesMakers();

		return Reply.formatReply(vehicles,ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/colours")  
	public String getColours( ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : getColours" );

		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");
		 List<Colour> colours= vehicleBean.getColours();

		return Reply.formatReply(colours,ExceptionCode.SCS);

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicle")  
	public String addUpdateVehicles(Vehicle vehicle ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : addUpdateVehicles" +  Utils.objectToJsonStirng(vehicle));

		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");

		if(vehicle.getVehicleId()==null){
			Integer vehicleId= vehicleBean.addVehicle(vehicle);
			vehicle.setVehicleId(vehicleId);
		}else{
			vehicleBean.updateVehicle(vehicle);
		}

		return Reply.formatReply(vehicle,ExceptionCode.SCS);
		
		

	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/user/vehicle")  
	public String addUpdateUserVehicle(UserVehicle vehicle ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : addUpdateUserVehicle" +  Utils.objectToJsonStirng(vehicle));

		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");

		if(vehicle.getVehicleId()==null){
			Integer vehicleId= vehicleBean.addVehicle(vehicle);
			vehicle.setVehicleId(vehicleId);
		}else{
			vehicleBean.updateVehicle(vehicle);
		}

		return Reply.formatReply(vehicle,ExceptionCode.SCS);
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/fileupload")  
	@Consumes("multipart/form-data")
	public String fileupload( @MultipartForm byte[] blob ) throws UnAuthorisedUser, UnActiveUser { 

		System.out.println("method : fileupload" +  Utils.objectToJsonStirng(blob));
		VehicleBean vehicleBean = (VehicleBean)InitialLoader.ctx.getBean("vehicleBean");
		
		VehiclePhoto photo = new VehiclePhoto();
		photo.setPhoto(blob);
		Integer photoId = vehicleBean.addVehiclePhoto(photo);
		return Reply.formatReply(photoId,ExceptionCode.SCS);
		
		

	}

}   