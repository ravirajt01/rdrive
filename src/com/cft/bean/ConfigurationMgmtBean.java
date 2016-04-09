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
import com.cft.pojo.Template;
import com.cft.pojo.User;
import com.cft.pojo.Vehicle;
import com.javatpoint.Employee;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.utility.Utils;


public class ConfigurationMgmtBean {

	HibernateTemplate template;  
	
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  

	
	
	public Integer addVehicle(Vehicle vehicle){  
		Integer vehicleId= (Integer) template.save(vehicle);
		return vehicleId;  
	}
	
	public void updateTemplate(Template templates){  
		template.update(templates);  
	}
	
	public void deleteVehicle(Vehicle vehicle){  
		template.delete(vehicle);  
	}
	
	public Vehicle getVehicleById(int id){  
		Vehicle v=(Vehicle)template.get(Vehicle.class,id);  
		return v;  
	}
	
	public List<Template> getTemplates(){  
		List<Template> list=new ArrayList<Template>();  
		list=template.loadAll(Template.class);  
		return list;  
	}
	
	public Template getTemplates(String mailType){  
		
		
		List<Template> templates =template.find("from Template where templateCode=? and isActive=?",mailType,true);
		
		
		Template template = templates.get(0);
		
		
		return template;  
	}
	
	
}
