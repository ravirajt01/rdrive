package com.cft.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.cft.pojo.Staging;
import com.cft.pojo.User;
import com.cft.pojo.Vehicle;
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
}
