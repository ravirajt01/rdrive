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
    
    Integer makeId;
    Integer modelId;
    Integer subModelId;
    Integer colourId;
    String fuel;
    String rtoNumber;
    String regNumber;
    String vehicleContact ;
    
    
    
    boolean isActive ;
    boolean isDeleted ;
	
    
    
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
	public String getVehicleContact() {
		return vehicleContact;
	}
	public void setVehicleContact(String vehicleContact) {
		this.vehicleContact = vehicleContact;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

    
    
}