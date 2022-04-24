package com.models;

public class CustomerModel  extends UserModel{
	
	private String address;
	private String postalCode;
	private String postal;

	public CustomerModel() {
		super();
	}
	
	public CustomerModel(int userID,  String firstName, String lastName, String email,
			String mobile, String password, String address, String postalCode) {
		super(userID, firstName, lastName, email, mobile, password);
		this.address = address ;
		this.postalCode = postalCode;
		
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
