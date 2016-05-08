package com.ss.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cft.bean.ConfigurationMgmtBean;
import com.cft.pojo.BookTour;
import com.cft.pojo.MailDetails;
import com.cft.pojo.Template;
import com.cft.pojo.User;
import com.cft.pojo.Vender;

public class CommunicateBean /*implements Communicable */{

	
	private static String fromEmail = "carfortour@gmail.com" ;
	private static String fromPW = "CarForTour2015" ;
	
	private static String domainName = "http://localhost:8080";
	// private static String domainName = "http://carfortour.com";
	private static String userVerifyRegLink = "/rest/user/verify?tk=";
	private static String venderVerifyRegLink = "/rest/vender/verify?tk=";
	
	ConfigurationMgmtBean configurationMgmtBean;
	
	public enum MailType {TOUR_BOOK , TOUR_BOOK_ADMIN , VRIFY_REG, VRIFYED_REG, FORGOT_PASS, VVRIFY_REG, VVRIFYED_REG};
	

	public ConfigurationMgmtBean getConfigurationMgmtBean() {
		return configurationMgmtBean;
	}

	public void setConfigurationMgmtBean(ConfigurationMgmtBean configurationMgmtBean) {
		this.configurationMgmtBean = configurationMgmtBean;
	}

	public void suscribe(String email) {
		main(email);
	}
	
	public void sendMail(MailType mailType ,Object object) {
		
		MailDetails mailDetails = new MailDetails();
		
		setMessageBody(mailType,object,mailDetails);
		sendMail(mailDetails.getToEmail() , fromEmail, fromPW,mailDetails.getSubject(),mailDetails.getBody());
		
		
	}
	
	
	public void sendTourBookMail(BookTour bookTour) {

		String body= null;
		String bodyToAdmin= null;
		body ="";// URLDecoder.decode(bookedRequestBodyEncoded, "UTF-8");
		bodyToAdmin ="";// URLDecoder.decode(bookedRequestBodyToAdminEncoded, "UTF-8");
		
		//sendBookedMailToAdmin
		//setMessageBody(MailType.TOUR_BOOK_ADMIN,bodyToAdmin,bookTour);
			//	sendMail(fromEmail, fromEmail, fromPW,bookedRequestSubjectForAdminc + bookTour.getEmail() ,bodyToAdmin );

		//sendBookedMailToCust
		//setMessageBody(MailType.TOUR_BOOK,body,bookTour);
				
				
				//	sendMail(bookTour.getEmail() , fromEmail, fromPW,bookedRequestSubject,body);
		
		

		
/*
		 EmployeeDao dao=(EmployeeDao)InitialLoader.ctx.getBean("edao");
		    int status= employeeDao.saveEmployee(new Employee(102,"Amit",35000));  
		    System.out.println(status);*/  
		/*
			Employee employee = new Employee();
			employee.setId(22);
			employee.setName("abc");
			employee.setSalary(100000);
			
		    employeeDao.saveEmployee(employee);  
		    System.out.println("saved..");*/
		    
	}
	
	
	public static void main(String email) {

		//URLDecoder ud = new java.net.URLDecoder();
		String suscribtionMailBody = null;
		suscribtionMailBody ="";// URLDecoder.decode(suscribtionBodyEncoded, "UTF-8");
		
	//	sendMail(email, fromEmail, fromPW,suscribtionSubject,suscribtionMailBody);
		
	}
	
	private static void sendMail(String sendTo, final String fromUser, final String frompassword,String subject , String body) {
		//Get the session object  
		Properties props = new Properties();  
	/*	props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class",  
				"javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");*/ 
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		System.out.println("conf set...");

		//Session session = Session.getDefaultInstance(props,  
		Session session = Session.getInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(fromUser,frompassword); 
			}  
		});  

		//compose message  
		try {  
			
			
			
			
			System.out.println("session:"+session);
			MimeMessage message = new MimeMessage(session);  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
			message.setSubject(subject);  
			//message.setText(body);  
			message.setContent(body,"text/html");

			//send message  
			Transport.send(message);  

			//System.out.println("message sent successfully");  

		} catch (MessagingException e) {throw new RuntimeException(e);}  


	}

	private  void setMessageBody(MailType mailType,Object bodyObject, MailDetails mailDetails) {

		BookTour bookTour =null;
		User user =null;
		Vender vender=null;
		String body = null;
		
		Template communicationTemplate = getMessageSubjectAndText( mailType);
		
		mailDetails.setSubject(communicationTemplate.getSubject());
		
		try {
			body = URLDecoder.decode(communicationTemplate.getText(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		switch (mailType) {
		
		case VRIFY_REG :

			user = (User) bodyObject;
			
			StringBuilder link = new StringBuilder();
			mailDetails.setToEmail(user.getEmail());
			
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#contactNumber", user.getContactNumber());
			link.append(domainName).append(userVerifyRegLink).append(user.getOtp());
			body = body.replace("#verificationLink",link );
			
			
			break;
			
		case VRIFYED_REG :

			user = (User) bodyObject;
			
			mailDetails.setToEmail(user.getEmail());
		
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#password", user.getPassword());
			body = body.replace("#contactNumber", user.getContactNumber());
			
			
			break;
		
		case TOUR_BOOK :
			
			bookTour = (BookTour) bodyObject;
			
			mailDetails.setToEmail( bookTour.getEmail());

			body = body.replace("#email", bookTour.getEmail());
			body = body.replace("#contactNumber", bookTour.getContactNumber());
			body = body.replace("#pickupLocation", bookTour.getFromLocation());
			body = body.replace("#dropOffLocation", bookTour.getToLocation());
			body = body.replace("#pickupDate", bookTour.getFromDate()+ " " + bookTour.getFromTime());
			//body = body.replace("#dropOffDate", bookTour.getToDate());
			if(bookTour.getVehicleDetails()!= null)
			body = body.replace("#vehicleDetails", bookTour.getVehicleDetails());	
			
			break;
			
			
		case TOUR_BOOK_ADMIN :
			
			bookTour = (BookTour) bodyObject;
			
			mailDetails.setToEmail(fromEmail);

			body = body.replace("#email", bookTour.getEmail());
			body = body.replace("#contactNumber", bookTour.getContactNumber());
			body = body.replace("#pickupLocation", bookTour.getFromLocation());
			body = body.replace("#dropOffLocation", bookTour.getToLocation());
			body = body.replace("#pickupDate", bookTour.getFromDate()+ " " + bookTour.getFromTime());
			//body = body.replace("#dropOffDate", bookTour.getToDate());
			if(bookTour.getVehicleDetails()!= null)
			body = body.replace("#vehicleDetails", bookTour.getVehicleDetails());	
			
			
			break;
			
		case FORGOT_PASS :

			user = (User) bodyObject;
			
			mailDetails.setToEmail(user.getEmail());
			
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#password", user.getPassword());
			body = body.replace("#contactNumber", user.getContactNumber());
			
			
			break;

			
		case VVRIFY_REG :

			vender = (Vender) bodyObject;
			
			StringBuilder vlink = new StringBuilder();
			mailDetails.setToEmail(vender.getEmail());
			
			body = body.replace("#name", vender.getName());
			body = body.replace("#email", vender.getEmail());
			body = body.replace("#contactNumber", vender.getContactNumber());
			vlink.append(domainName).append(venderVerifyRegLink).append(vender.getOtp());
			body = body.replace("#verificationLink",vlink );
			
			
			break;
		case VVRIFYED_REG :

			vender = (Vender) bodyObject;
			
			mailDetails.setToEmail(vender.getEmail());
			
			body = body.replace("#name", vender.getName());
			body = body.replace("#email", vender.getEmail());
			body = body.replace("#password", vender.getPassword());
			body = body.replace("#contactNumber", vender.getContactNumber());
			
			
			break;
			
		default:
			break;
		}
		
		mailDetails.setBody(body);
		//System.out.println("body"+body  );
	}

	private Template getMessageSubjectAndText(MailType mailType) {
		
		return configurationMgmtBean.getTemplates(mailType.toString());
		
	}

	private static void sendMail(String sendTo, final String fromUser, final String frompassword,String subject , String body,int a) {
		//Get the session object  
		Properties props = new Properties();  
	/*	props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class",  
				"javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");*/ 
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		System.out.println("conf set...");

		//Session session = Session.getDefaultInstance(props,  
		Session session = Session.getInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(fromUser,frompassword); 
			}  
		});  

		//compose message  
		try {  
			
			System.out.println("session:"+session);
			MimeMessage message = new MimeMessage(session);  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
			message.setSubject(subject);  
			//message.setText(body);  
			message.setContent(body,"text/html");

			//send message  
			Transport.send(message);  

			System.out.println("message sent successfully");  

		} catch (MessagingException e) {throw new RuntimeException(e);}  
	}
}
