package com.cft.entity;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "sub_model")
public class SubModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer subModelId;
    
    String subModel;
    String subModelCode ;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="modelId",insertable=false,updatable=false)
    private Model model ;

	public Integer getSubModelId() {
		return subModelId;
	}

	public void setSubModelId(Integer subModelId) {
		this.subModelId = subModelId;
	}

	public String getSubModel() {
		return subModel;
	}

	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}

	public String getSubModelCode() {
		return subModelCode;
	}

	public void setSubModelCode(String subModelCode) {
		this.subModelCode = subModelCode;
	}
    
}