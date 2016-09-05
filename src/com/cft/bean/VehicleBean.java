package com.cft.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.entity.Colour;
import com.cft.entity.Make;
import com.cft.entity.Model;
import com.cft.entity.SubModel;
import com.cft.entity.UserVehicle;
import com.cft.entity.UserVehicleView;
import com.cft.entity.Vehicle;
import com.cft.entity.VehiclePhoto;
import com.cft.exception.RecordAlreadyExist;


public class VehicleBean {


	HibernateTemplate template;  
	
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  

	
	
	public Integer addVehicle(Vehicle vehicle){  
		Integer vehicleId= (Integer) template.save(vehicle);
		return vehicleId;  
	}
	
	public void updateVehicle(Vehicle vehicle){  
		template.update(vehicle);  
	}
	
	public Integer addMake(Make make) throws RecordAlreadyExist{  
		Integer makeId= null; 
		try{
			makeId= (Integer) template.save(make);
		}catch(DataIntegrityViolationException e){
			throw new RecordAlreadyExist("RecordAlreadyExist");
		}
		
		return makeId;  
	}

	public void updateMake(Make make){  
		template.update(make);  
	}
	
	
	
	public Integer addModel(Model model) throws RecordAlreadyExist{  
		Integer modelId= null; 
		try{
			modelId= (Integer) template.save(model);
		}catch(DataIntegrityViolationException e){
			throw new RecordAlreadyExist("RecordAlreadyExist");
		}
		
		return modelId;  
	}
	public void updateModel(Model model){  
		template.update(model);  
	}
	
	
	public Integer addSubModel(SubModel subModel) throws RecordAlreadyExist{  
		Integer subModelId= null; 
		try{
			subModelId= (Integer) template.save(subModel);
		}catch(DataIntegrityViolationException e){
			throw new RecordAlreadyExist("RecordAlreadyExist");
		}
		
		return subModelId;  
	}
	public void updateSubModel(SubModel subModel){  
		template.update(subModel);  
	}
	
	
	public Integer addUserVehicle(UserVehicle userVehicle){  
		Integer userVehicleId= (Integer) template.save(userVehicle);
		return userVehicleId;  
	}
	
	public void updateUserVehicle(UserVehicle userVehicle){  
		template.update(userVehicle);  
	}
	
	
	public void deleteVehicle(Vehicle vehicle){  
		template.delete(vehicle);  
	}
	
	public Vehicle getVehicleById(int id){  
		Vehicle v=(Vehicle)template.get(Vehicle.class,id);  
		return v;  
	}
	
	public List<Vehicle> getVehicles(){  
		List<Vehicle> list=new ArrayList<Vehicle>();  
		list=template.loadAll(Vehicle.class);  
		return list;  
	}



	public List<Make> getVehiclesMakers() {
		List<Make> list=new ArrayList<Make>();  
		list=template.loadAll(Make.class);  
		return list;  
	}



	public List<Colour> getColours() {
		List<Colour> list=new ArrayList<Colour>();  
		list=template.loadAll(Colour.class);  
		return list;  
	}

	public List<UserVehicleView> getUserVehicles(Integer userId){  
		
		List<UserVehicleView> userVehicles=new ArrayList<UserVehicleView>();
		
		if(userId != null)
			userVehicles=template.find("from UserVehicleView where userId = ? order by UserVehicleId",userId); 
		else
			userVehicles=template.loadAll(UserVehicleView.class);
		 
		return userVehicles;  
	}


	public Integer addVehiclePhoto(VehiclePhoto vehiclePhoto) {
		
		Integer vehiclephotoId= (Integer) template.save(vehiclePhoto);
		return vehiclephotoId;  
		
	}
}
