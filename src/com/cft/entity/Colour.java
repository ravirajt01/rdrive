package com.cft.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "colour")
public class Colour {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer colourId;
    
    String colour;
    String colourCode ;
    
    
	public Integer getColourId() {
		return colourId;
	}
	public void setColourId(Integer colourId) {
		this.colourId = colourId;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getColourCode() {
		return colourCode;
	}
	public void setColourCode(String colourCode) {
		this.colourCode = colourCode;
	}
	
    
}