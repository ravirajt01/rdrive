package com.cft.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "vehicle_photo")
public class VehiclePhoto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column(name = "id")
    private Integer vehiclePhotoId;
    
    String VehicleId;
    
    byte[] photo;

	public Integer getVehiclePhotoId() {
		return vehiclePhotoId;
	}

	public void setVehiclePhotoId(Integer vehiclePhotoId) {
		this.vehiclePhotoId = vehiclePhotoId;
	}

	public String getVehicleId() {
		return VehicleId;
	}

	public void setVehicleId(String vehicleId) {
		VehicleId = vehicleId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
    
    
    
}