package com.cft.entity;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name= "vender")  

public class Vender {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(nullable = false , unique = true )
	Integer venderId ;
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

	public Integer getVenderId() {
		return venderId;
	}

	public void setVenderId(Integer venderId) {
		this.venderId = venderId;
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




}
