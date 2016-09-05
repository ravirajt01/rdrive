package com.cft.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name= "user_role2")  
public class UserRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer userRoleId ;
	Integer userId ;
	Integer roleId ;
	
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId",insertable=false,updatable=false)
	private User user;
	*/
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId",insertable=false,updatable=false)
	private User user;*/
	
	/*
	public Integer getUserRoleId() {
		return userRoleId;
	}*/
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	/*public Integer getUserId() {
		return userId;
	}*/
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
