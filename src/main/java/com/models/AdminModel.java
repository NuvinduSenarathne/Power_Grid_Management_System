package com.models;

public class AdminModel {
	
	private int adminID;
	private String firstName;
	private String lastName;
	private String NIC;
	private String email;
	private String mobile;
	private String department;
	private String password;
	
	public AdminModel() {
		super();
	}
	
	public AdminModel(int adminID, String firstName, String lastName, String NIC, String email, String mobile,
			String department, String password) {
		super();
		this.adminID = adminID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.NIC = NIC;
		this.email = email;
		this.mobile = mobile;
		this.department = department;
		this.password = password;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
