package com.cft.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.pojo.Staging;
import com.cft.pojo.User;
import com.javatpoint.Employee;
import com.ss.utility.Utils;


public class StagingBean {

	HibernateTemplate template;  
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  
	
	public void addStaging(Staging staging){  
		template.save(staging);  
	}
	
	public void updateStaging(Staging staging){  
		template.update(staging);  
	}
	
	public void deleteStaging(Staging staging){  
		template.delete(staging);  
	}
	
	public Staging getStagingById(int id){  
		Staging u=(Staging)template.get(Staging.class,id);  
		return u;  
	}
	
	
}
