package com.cft.ws;  
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.cft.bean.VehicleBean;
import com.cft.entity.Colour;
import com.cft.entity.Make;
import com.cft.entity.Model;
import com.cft.entity.SubModel;
import com.cft.entity.Vehicle;
import com.cft.entity.VehiclePhoto;
import com.cft.exception.RecordAlreadyExist;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.javatpoint.InitialLoader;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;
import com.ss.utility.Utils;
@Path("/")  

public class VehicleServices {  

	private static final Logger logger = Logger.getLogger(VehicleServices.class.getName());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicles")  
	public String getVehicles( ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : getVehicles" );

		VehicleBean vehicleBean =InitialLoader.ctx.getBean(VehicleBean.class);
		 List<Vehicle> vehicles= vehicleBean.getVehicles();

		return Reply.formatReply(vehicles,ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicles/make")  
	public String getVehiclesMakers( ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : getVehiclesMakers" );

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);
		 List<Make> vehicles= vehicleBean.getVehiclesMakers();

		return Reply.formatReply(vehicles,ExceptionCode.SCS);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/colours")  
	public String getColours( ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : getColours" );

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);
		 List<Colour> colours= vehicleBean.getColours();

		return Reply.formatReply(colours,ExceptionCode.SCS);

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/vehicle")  
	public String addUpdateVehicles(Vehicle vehicle ) throws UnAuthorisedUser, UnActiveUser { 

		logger.info("method : addUpdateVehicles" +  Utils.objectToJsonStirng(vehicle));

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);

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

		logger.info("method : fileupload" +  Utils.objectToJsonStirng(blob));
		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);
		
		VehiclePhoto photo = new VehiclePhoto();
		photo.setPhoto(blob);
		Integer photoId = vehicleBean.addVehiclePhoto(photo);
		return Reply.formatReply(photoId,ExceptionCode.SCS);

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/make")  
	public String addUpdateMake(Make make ) throws UnAuthorisedUser, UnActiveUser, RecordAlreadyExist { 

		logger.info("method : addUpdateMake" +  Utils.objectToJsonStirng(make));

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);

		if(make.getMakeId()==null){
			Integer makeId= vehicleBean.addMake(make);
			make.setMakeId(makeId);
		}else{
			vehicleBean.updateMake(make);
		}

		return Reply.formatReply(make,ExceptionCode.SCS);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/model")  
	public String addUpdateModel(Model model ) throws UnAuthorisedUser, UnActiveUser, RecordAlreadyExist { 

		logger.info("method : addUpdateMake" +  Utils.objectToJsonStirng(model));

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);

		if(model.getModelId()==null){
			Integer makeId= vehicleBean.addModel(model);
			model.setModelId(makeId);
		}else{
			vehicleBean.updateModel(model);
		}

		return Reply.formatReply(model,ExceptionCode.SCS);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/sub_model")  
	public String addUpdateSubModel(SubModel subModel ) throws UnAuthorisedUser, UnActiveUser, RecordAlreadyExist { 

		logger.info("method : addUpdateMake" +  Utils.objectToJsonStirng(subModel));

		VehicleBean vehicleBean = InitialLoader.ctx.getBean(VehicleBean.class);

		if(subModel.getSubModelId()==null){
			Integer makeId= vehicleBean.addSubModel(subModel);
			subModel.setSubModelId(makeId);
		}else{
			vehicleBean.updateSubModel(subModel);
		}

		return Reply.formatReply(subModel,ExceptionCode.SCS);
	}
	

}   