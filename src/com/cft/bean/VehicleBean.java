package com.cft.bean;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.entity.Colour;
import com.cft.entity.Make;
import com.cft.entity.Staging;
import com.cft.entity.User;
import com.cft.entity.Vehicle;
import com.cft.entity.VehiclePhoto;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.javatpoint.Employee;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.utility.Utils;


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



	public Integer addVehiclePhoto(VehiclePhoto vehiclePhoto) {
		
		Integer vehiclephotoId= (Integer) template.save(vehiclePhoto);
		return vehiclephotoId;  
		
	}
}
