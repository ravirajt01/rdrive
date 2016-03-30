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
import com.javatpoint.Employee;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.utility.Utils;


public class UserBean {

	HibernateTemplate template;  
	
	StagingBean stagingBean;
	User user;
	
	CommunicateBean communicateBean;
	
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  

	public void setStagingBean(StagingBean stagingBean) {
		this.stagingBean = stagingBean;
	}
	
	public void setCommunicateBean(CommunicateBean communicateBean) {
		this.communicateBean = communicateBean;
	}

	public void registerUser(User user) throws UserAlreadyExist{  

		List<User> users = template.find("from User where email=?",user.getEmail());
		if(users.size()!=0)
			throw new UserAlreadyExist("User Already Exist")  ;

		user.setRegistrationDate(new Date());
		Integer userId =  (Integer) template.save(user);  

		System.out.println("userId : "+ userId);

		boolean isDuplicate = true;
		String emailToken = null;
		while (isDuplicate ){

			emailToken = Utils.generateRandomCode();

			List<Staging> recordForToken =template.find("from Staging where otp=?",emailToken);
			if(recordForToken.size() == 0)
				isDuplicate =  false ;
		}


		System.out.println("emailToken :"+ emailToken);

		Staging staging = new Staging();
		staging.setOtp(emailToken);
		staging.setUserId(user.getUserId());

		stagingBean.addStaging(staging);


		user.setOtp(emailToken);
		communicateBean.sendMail(MailType.VRIFY_REG ,user );

	}
	
	public void addUser(User u){  
		template.save(u);  
	}
	
	public void updateUser(User u){  
		template.update(u);  
	}
	
	public void deleteUser(User e){  
		template.delete(e);  
	}
	
	public User getUserById(int id){  
		User u=(User)template.get(User.class,id);  
		return u;  
	}
	
	public List<User> getUsers(){  
		List<User> list=new ArrayList<User>();  
		list=template.loadAll(User.class);  
		return list;  
	}

	public int verifyUserRegistration(String token) {
		
		
		List<Staging> userToVerify =template.find("from Staging where otp=?",token);
		
		System.out.println("tokenForuserToVerify"+userToVerify.size());
		
		if(userToVerify.size()==0){
			return 0;
		}
		
		
		// delete stating entry
		stagingBean.deleteStaging(userToVerify.get(0));
		// update details
		User veriredUserDetails =  this.getUserById(userToVerify.get(0).getUserId());
		veriredUserDetails.setRegistrationConfirmed(true);
		veriredUserDetails.setRegistrationConfirmDate(new Date());
		this.updateUser(veriredUserDetails);
		
		communicateBean.sendMail(MailType.VRIFYED_REG, veriredUserDetails);
		
		return 1;
	}

	public User loginUser(User user) throws UnAuthorisedUser, UnActiveUser {
		
		User loggedInUser =null;
		List<User> users = template.find("from User where email=? and password =?",user.getEmail(),user.getPassword());
		
		if(users.size()==0)
			throw new UnAuthorisedUser("User does not exist")  ;
		else{
				
			loggedInUser = users.get(0);
			
			if(!loggedInUser.isRegistrationConfirmed()){
				throw new UnActiveUser("User is not active yet")  ;
			}
				
		}
		
		return loggedInUser;
		
	}  
	
	

	public List<User> getAllUsers(){
		
		return this.getUsers();
		
	}
	
	
	
public void forgotpass(User user) throws UnAuthorisedUser {
	User reguser =null;
		List<User> users = template.find("from User where email=?",user.getEmail());
		
		if(users.size()==0)
			throw new UnAuthorisedUser("User does not exist")  ;
		else{
			reguser = users.get(0);
			communicateBean.sendMail(MailType.FORGOT_PASS ,reguser );
			
		}
		//return reguser;
		
	}  
	
	
	
	
}
