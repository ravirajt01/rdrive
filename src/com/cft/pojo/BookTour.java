package com.cft.pojo;

import java.util.Date;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
//import org.hibernate.annotations.Entity;


@Entity
@Table(name= "booking_details") 
public class BookTour {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	Integer bookingId ;
	Integer userId;
	
=======
public class BookTour {
	
>>>>>>> dab752e23f52a371f6634108c0ad4f6cfa666054
	String email ;
	String contactNumber ; 
	
	String fromDate ;
<<<<<<< HEAD
	//String toDate ;
=======
	String toDate ;
>>>>>>> dab752e23f52a371f6634108c0ad4f6cfa666054
	
	String fromTime ;
	
	String fromLocatiom;
	String toLocation ;
	
	String vehicleDetails ;
<<<<<<< HEAD
	Date bookingDate;
	
	
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
=======
	
	
	
>>>>>>> dab752e23f52a371f6634108c0ad4f6cfa666054
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getFromLocatiom() {
		return fromLocatiom;
	}
	public void setFromLocatiom(String fromLocatiom) {
		this.fromLocatiom = fromLocatiom;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
<<<<<<< HEAD
/*	public String getToDate() {
=======
	public String getToDate() {
>>>>>>> dab752e23f52a371f6634108c0ad4f6cfa666054
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
<<<<<<< HEAD
	}*/
=======
	}
>>>>>>> dab752e23f52a371f6634108c0ad4f6cfa666054
	public String getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	
	

}
