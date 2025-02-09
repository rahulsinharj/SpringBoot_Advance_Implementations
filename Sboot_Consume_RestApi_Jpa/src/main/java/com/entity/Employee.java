package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Employee_Consumed_Data")
public class Employee {

	@Id
	private int id;
	private String name;
	private String email;
	private String gender;
									// Agar external API me actually 'status' name ke jagah koi aur name ("empstatus") se wo property return ho raha ho 
//  	@JsonProperty("empstatus")					// in that case, we have to put this ANNOTATION according to actual property name coming from external consumed API, for doing proper naming convention in our Application, that we will store in DB		
	private String status;			
	
	
	public Employee(int id, String name, String email, String gender, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	public Employee() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender + ", status="
				+ status + "]";
	}
	
	
}
