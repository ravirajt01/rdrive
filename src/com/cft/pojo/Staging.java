package com.cft.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "staging")  
public class Staging {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer stagingId ;
	
	Integer userId ;
	
	String otp;
	
	int stagingType = 1;

	public Integer getStagingId() {
		return stagingId;
	}

	public void setStagingId(Integer stagingId) {
		this.stagingId = stagingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getStagingType() {
		return stagingType;
	}

	public void setStagingType(int stagingType) {
		this.stagingType = stagingType;
	}
	
	
	
	

}
