package com.cft.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.entity.Staging;
import com.cft.entity.Template;
import com.cft.entity.User;
import com.cft.entity.Vehicle;
import com.cft.exception.UnActiveUser;
import com.cft.exception.UnAuthorisedUser;
import com.cft.exception.UserAlreadyExist;
import com.javatpoint.Employee;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.utility.Utils;


public class ConfigurationMgmtBean {

	HibernateTemplate template;  
	
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  

	
	
	public Integer addTemplate(Template templates){  
		Integer templatesId= (Integer) template.save(templates);
		return templatesId;  
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
