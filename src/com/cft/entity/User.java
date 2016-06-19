package com.cft.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;




@Entity
@Table(name= "user")  
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(nullable = false , unique = true )
	Integer userId ;
	String name;
	String contactNumber ;
	String email ;
	String password;
	
	Date registrationDate;
	boolean isRegistrationConfirmed ;
	Date registrationConfirmDate;
	@Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	Date lastTimeStamp;
	
	@Transient
	String otp;
	
	/*@OneToOne( mappedBy = "user")
	UserRole userRole;
	*/
	
	@OneToMany( mappedBy = "user",fetch=FetchType.EAGER)
	List<UserRole> userRoles;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isRegistrationConfirmed() {
		return isRegistrationConfirmed;
	}

	public void setRegistrationConfirmed(boolean isRegistrationConfirmed) {
		this.isRegistrationConfirmed = isRegistrationConfirmed;
	}

	public Date getRegistrationConfirmDate() {
		return registrationConfirmDate;
	}

	public void setRegistrationConfirmDate(Date registrationConfirmDate) {
		this.registrationConfirmDate = registrationConfirmDate;
	}

	public Date getLastTimeStamp() {
		return lastTimeStamp;
	}

	public void setLastTimeStamp(Date lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
	

	

}
