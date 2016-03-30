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
import com.cft.pojo.Vender;
import com.javatpoint.Employee;
import com.ss.bean.CommunicateBean;
import com.ss.bean.CommunicateBean.MailType;
import com.ss.utility.Utils;




public class VenderBean {

HibernateTemplate template;  
	
	StagingBean stagingBean;
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
	
	
	public void addVender(Vender v){  
		template.save(v);  
	}
	
	public void updateVender(Vender v){  
		template.update(v);  
	}
	
	public void deleteVender(Vender v){  
		template.delete(v);  
	}
	
	public Vender getVenderById(int id){  
		Vender v=(Vender)template.get(Vender.class,id);  
		return v;  
	}
	
	public List<Vender> getVenders(){  
		List<Vender> list=new ArrayList<Vender>();  
		list=template.loadAll(Vender.class);  
		return list;  
	}
	
	
	public void registerVender(Vender vender) throws UserAlreadyExist{  

		List<Vender> venders = template.find("from Vender where email=?",vender.getEmail());
		if(venders.size()!=0)
			throw new UserAlreadyExist("User Already Exist")  ;

		vender.setRegistrationDate(new Date());
		Integer venderId =  (Integer) template.save(vender);  

		System.out.println("venderId : "+ venderId);

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
		staging.setUserId(vender.getVenderId());

		stagingBean.addStaging(staging);


		vender.setOtp(emailToken);
		communicateBean.sendMail(MailType.VVRIFY_REG ,vender );

	}
	
	
public int verifyVenderRegistration(String token) {
		
		
		List<Staging> venderToVerify =template.find("from Staging where otp=?",token);
		
		System.out.println("tokenForvenderToVerify"+venderToVerify.size());
		
		if(venderToVerify.size()==0){
			return 0;
		}
		
		
		// delete stating entry
		stagingBean.deleteStaging(venderToVerify.get(0));
		// update details
		Vender veriredVenderDetails =  this.getVenderById(venderToVerify.get(0).getUserId());
		veriredVenderDetails.setRegistrationConfirmed(true);
		veriredVenderDetails.setRegistrationConfirmDate(new Date());
		this.updateVender(veriredVenderDetails);
		
		communicateBean.sendMail(MailType.VVRIFYED_REG, veriredVenderDetails);
		
		return 1;
	}



public Vender loginVender(Vender vender) throws UnAuthorisedUser, UnActiveUser {
	
	Vender loggedInVender =null;
	List<Vender> Venders = template.find("from Vender where email=? and password =?",vender.getEmail(),vender.getPassword());
	
	if(Venders.size()==0)
		throw new UnAuthorisedUser("User does not exist")  ;
	else{
			
		loggedInVender = Venders.get(0);
		
		if(!loggedInVender.isRegistrationConfirmed()){
			throw new UnActiveUser("User is not active yet")  ;
		}
			
	}
	
	return loggedInVender;
	
}  



public List<Vender> getAllVenders(){
	
	return this.getVenders();
	
}


	
}
