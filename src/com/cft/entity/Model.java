package com.cft.entity;
 
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer modelId;
    
    String model;
    String modelCode ;
    
    @OneToMany( mappedBy = "model",fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SubModel> subModels ;
    

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="makeId",insertable=false,updatable=false)
    private Make make ;
    
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public List<SubModel> getSubModels() {
		return subModels;
	}
	public void setSubModels(List<SubModel> subModels) {
		this.subModels = subModels;
	}
    
    
}