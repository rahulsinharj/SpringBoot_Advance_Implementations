package com.entity;

public class Address {

	private String doorNo;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	public Address() {
		super();
	}
	public Address(String doorNo, String street, String city, String state, String zipCode) {
		super();
		this.doorNo = doorNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
