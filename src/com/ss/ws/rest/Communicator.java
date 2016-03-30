package com.ss.ws.rest; 

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Body;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cft.bean.BookTourBean;
import com.cft.pojo.BookTour;
import com.javatpoint.Employee;
import com.javatpoint.EmployeeDao;
import com.javatpoint.InitialLoader;
import com.javatpoint_old.AccountsDao;
import com.ss.bean.Autheticable;
import com.ss.bean.AutheticatorBean;
import com.ss.bean.Communicable;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.pojo.LoginUser;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;
import com.ss.utility.Utils;
import com.studytrails.tutorials.springhibernatejpa.Person;
import com.studytrails.tutorials.springhibernatejpa.PersonDao;
@Path("/")  

public class Communicator {  
/*
	@Autowired
	private PersonDao personDao;*/
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/suscribe")  
	public String suscribe( @QueryParam(value = "email") String email ){ 

		System.out.println("method : suscribe"  );
		
		CommunicateBean communicable = new CommunicateBean();
		
		communicable.suscribe(email);
				
		System.out.println("input : "+ email);

		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/book_tour")  
	public String putTourBookRequest( BookTour bookTour  ){ 

		System.out.println("method : putTourBookRequest" +"input : "+ Utils.objectToJsonStirng(bookTour) );

		BookTourBean booktourBean=(BookTourBean)InitialLoader.ctx.getBean("booktourBean");
		booktourBean.addBookTour(bookTour);
		//CommunicateBean communicable = new CommunicateBean();
		
		CommunicateBean communicateBean=(CommunicateBean)InitialLoader.ctx.getBean("communicateBean");
		
		/*
		if(bookTour.getFromDate()!=null)
		bookTour.setFromDate(bookTour.getFromDate().substring(0, 10));
		if(bookTour.getToDate()!=null)
		bookTour.setToDate(bookTour.getToDate().substring(0,10));*/
		
		communicateBean.sendMail(MailType.TOUR_BOOK, bookTour);
		communicateBean.sendMail(MailType.TOUR_BOOK_ADMIN, bookTour);
	   // ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");  

				
		return Reply.formatReply("",ExceptionCode.SCS);

	}
	
	

}   