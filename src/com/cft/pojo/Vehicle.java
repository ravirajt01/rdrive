package com.cft.pojo;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column(name = "id")
    private Integer vehicleId;
   /* @Column(name = "company")
    private String company;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private long price;*/
    
    String name;
    String code ;
    Integer wheels;
    Integer seats;
    Integer basicCharge;
    Integer extraChargePerKm;
    Integer extraChargePerHour;
    Integer outStationRidePerKm;
    
    
    boolean isActive ;
    boolean isDeleted ;
    
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWheels() {
		return wheels;
	}
	public void setWheels(Integer wheels) {
		this.wheels = wheels;
	}
	
	
	
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getBasicCharge() {
		return basicCharge;
	}
	public void setBasicCharge(Integer basicCharge) {
		this.basicCharge = basicCharge;
	}
	public Integer getExtraChargePerKm() {
		return extraChargePerKm;
	}
	public void setExtraChargePerKm(Integer extraChargePerKm) {
		this.extraChargePerKm = extraChargePerKm;
	}
	public Integer getExtraChargePerHour() {
		return extraChargePerHour;
	}
	public void setExtraChargePerHour(Integer extraChargePerHour) {
		this.extraChargePerHour = extraChargePerHour;
	}
	public Integer getOutStationRidePerKm() {
		return outStationRidePerKm;
	}
	public void setOutStationRidePerKm(Integer outStationRidePerKm) {
		this.outStationRidePerKm = outStationRidePerKm;
	}
	
    
    
    
}