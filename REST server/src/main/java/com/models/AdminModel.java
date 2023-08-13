package com.models;

public class AdminModel extends UserModel {
	
	private String serviceNo;
	private String department;
	private String position;
	
	public AdminModel() {
		super();
	}
	
	public AdminModel(int userID,  String firstName, String lastName, String email,
			String mobile, String password, String serviceNo, String department, String position) {
		super(userID, firstName, lastName, email, mobile, password);
		this.serviceNo = serviceNo;
		this.department = department;
		this.position = position;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
