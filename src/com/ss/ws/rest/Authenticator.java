package com.ss.ws.rest;  
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javatpoint_old.AccountsDao;
import com.ss.bean.Autheticable;
import com.ss.bean.AutheticatorBean;
import com.ss.pojo.LoginUser;
import com.ss.utility.GenericVariables.ExceptionCode;
import com.ss.utility.Reply;
import com.ss.utility.Utils;
import com.studytrails.tutorials.springhibernatejpa.Person;
import com.studytrails.tutorials.springhibernatejpa.PersonDao;
@Path("/")  
public class Authenticator {  
/*
	@Autowired
	private PersonDao personDao;*/
	
	@POST  
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/login")  
	public String login( LoginUser loginUser){ 

		System.out.println("method : login"  );
		
		Autheticable autheticable = new AutheticatorBean();
		
		String token = autheticable.login(loginUser);
		
		System.out.println("input : "+ Utils.objectToJsonStirng(loginUser));
		
	/*	Person p = new Person() ;
		p.setName(loginUser.getUserName());
		p.setEmail(loginUser.getPassword());
		
		PersonDao personDao = new PersonDao();
		
		personDao.insert(p);
		
		java.util.List<Person> a = personDao.selectAll();*/
		

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountsDao accountsDao = context.getBean("accountsDaoBean",AccountsDao.class);
		
		 accountsDao.createAccount(15, "Jai Kumar", 41000);  
		 accountsDao.createAccount(20, "Rishi ", 35000);  
		 System.out.println("Accounts created");  
		
		
		return Reply.formatReply("",ExceptionCode.SCS);
		
		//return Reply.formatReply(loginUser,"SCS");
		
		//return Response.status(500).entity(Reply.formatReply((Object)loginUser,"SCS")).build();

	}

}   