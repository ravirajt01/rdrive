package com.cft.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.pojo.City;
import com.ss.utility.Utils;


public class CityBean {
	
HibernateTemplate template;  
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  
	
	public Integer addCity(City city){  
		Integer cityId=(Integer)template.save(city);
		return cityId; 
	}
	
	public void updateCity(City city){  
		template.update(city);  
	}
	
	public void deleteCity(City city){  
		template.delete(city);  
	}
	
	public City getCityById(int id){  
		City c=(City)template.get(City.class,id);  
		return c;  
	}

	public List<City> getcities(){  
		List<City> list=new ArrayList<City>();  
		list=template.loadAll(City.class);  
		return list;  
	}

}
