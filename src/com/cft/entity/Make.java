package com.cft.entity;
 
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "make")
public class Make {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer makeId;
    
    String make;
    String makeCode ;
    
    @OneToMany( mappedBy = "make",fetch=FetchType.EAGER)
//    @OneToMany( mappedBy = "make")
    List<Model> models;
    
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getMakeCode() {
		return makeCode;
	}
	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}
	public Integer getMakeId() {
		return makeId;
	}
	public void setMakeId(Integer makeId) {
		this.makeId = makeId;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	
    
    
    
}