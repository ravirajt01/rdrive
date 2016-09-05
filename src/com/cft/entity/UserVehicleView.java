package com.cft.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user_vehicle_view")
public class UserVehicleView {

	
    @Id
   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userVehicleId;
    private Integer userId;
    private String name;
    
    private String make;
    private String model;
    private String subModel;
    
    private String colour;
    private String fuel;
    private String rtoNumber;
    private String regNumber;
    
    private String vehicleContactNumber ;
    private String vehicleContactEmail ;
    
    private boolean isActive;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSubModel() {
		return subModel;
	}
	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    

    
    
}