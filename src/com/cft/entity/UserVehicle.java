package com.cft.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user_vehicle")
public class UserVehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userVehicleId;
    private Integer userId;
    
    private Integer makeId;
    private Integer modelId;
    private Integer subModelId;
    private Integer colourId;
    private String fuel;
    private String rtoNumber;
    private String regNumber;
    private String vehicleContactNumber ;
    private String vehicleContactEmail ;
    
    
	public Integer getUserVehicleId() {
		return userVehicleId;
	}
	public void setUserVehicleId(Integer userVehicleId) {
		this.userVehicleId = userVehicleId;
	}
	public Integer getMakeId() {
		return makeId;
	}
	public void setMakeId(Integer makeId) {
		this.makeId = makeId;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Integer getSubModelId() {
		return subModelId;
	}
	public void setSubModelId(Integer subModelId) {
		this.subModelId = subModelId;
	}
	public Integer getColourId() {
		return colourId;
	}
	public void setColourId(Integer colourId) {
		this.colourId = colourId;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getRtoNumber() {
		return rtoNumber;
	}
	public void setRtoNumber(String rtoNumber) {
		this.rtoNumber = rtoNumber;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	
	public String getVehicleContactNumber() {
		return vehicleContactNumber;
	}
	public void setVehicleContactNumber(String vehicleContactNumber) {
		this.vehicleContactNumber = vehicleContactNumber;
	}
	public String getVehicleContactEmail() {
		return vehicleContactEmail;
	}
	public void setVehicleContactEmail(String vehicleContactEmail) {
		this.vehicleContactEmail = vehicleContactEmail;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

    
    
}