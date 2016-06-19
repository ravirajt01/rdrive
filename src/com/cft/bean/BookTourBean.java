package com.cft.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hsqldb.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cft.entity.BookTour;
import com.cft.entity.Staging;
import com.cft.entity.User;

public class BookTourBean {
	User user;
HibernateTemplate template; 
 Integer id;
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  
	
	public void addBookTour(BookTour bookTour){  
	
			bookTour.setBookingDate(new Date());
		template.save(bookTour);  
	}
	
	public void updateBookTour(BookTour bookTour){  
		template.update(bookTour);  
	}
	
	public void deleteBookTour(BookTour bookTour){  
		template.delete(bookTour);  
	}
	
	public BookTour getBookTourById(int id){  
		BookTour bookTour=(BookTour)template.get(BookTour.class,id);  
		return bookTour;  
	}
	public List<BookTour> getbookings(){  
		List<BookTour> list=new ArrayList<BookTour>();  
		
		list=template.loadAll(BookTour.class);
		//List<Staging> recordForToken =template.find("from Staging where otp=?",emailToken);
		
		return list;  
	}
	
}
